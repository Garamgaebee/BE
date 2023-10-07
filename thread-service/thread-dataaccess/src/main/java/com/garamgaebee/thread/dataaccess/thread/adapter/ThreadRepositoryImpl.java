package com.garamgaebee.thread.dataaccess.thread.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.thread.dataaccess.thread.entity.LikeEntity;
import com.garamgaebee.thread.dataaccess.thread.entity.ThreadEntity;
import com.garamgaebee.thread.dataaccess.thread.mapper.ThreadAccessMapper;
import com.garamgaebee.thread.dataaccess.thread.repository.LikeJpaRepository;
import com.garamgaebee.thread.dataaccess.thread.repository.ThreadImageJpaRepository;
import com.garamgaebee.thread.dataaccess.thread.repository.ThreadJpaRepository;
import com.garamgaebee.thread.domain.dto.DeleteThreadRes;
import com.garamgaebee.thread.domain.entity.Like;
import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.entity.ThreadStatus;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ThreadRepositoryImpl implements ThreadRepository {

    private final ThreadAccessMapper mapper;
    private final ThreadJpaRepository threadJpaRepository;
    private final ThreadImageJpaRepository imageJpaRepository;
    private final LikeJpaRepository likeJpaRepository;

    @Autowired
    public ThreadRepositoryImpl(ThreadAccessMapper mapper, ThreadJpaRepository threadJpaRepository, ThreadImageJpaRepository imageJpaRepository, LikeJpaRepository likeJpaRepository) {
        this.mapper = mapper;
        this.threadJpaRepository = threadJpaRepository;
        this.imageJpaRepository = imageJpaRepository;
        this.likeJpaRepository = likeJpaRepository;
    }

    /**
     * 스레드 생성 (팀, 개인 공용)
     * */
    @Override
    public Thread createThread(Thread createTarget) {
        //엔티티 생성해서
        ThreadEntity entity = mapper.threadToEntity(createTarget);

        //저장하고
        threadJpaRepository.save(entity);
        //Service 단에서 트랜잭션으로 묶어두었기 때문에 강제 플러시
        threadJpaRepository.flush();

        //이미지와 좋아요 엔티티 초기화
        entity.insertImages(mapper.imageToEntity(createTarget.getImgUrls(), entity.getThreadIdx()));
        entity.insertLike(new ArrayList<>());

        //리턴
        return mapper.entityToThread(entity);
    }

    /**
     * 스레드 삭제
     * */
    @Override
    public DeleteThreadRes deleteThread(String threadIdx) throws BaseException{
        ThreadEntity entity = threadJpaRepository.findById(UUID.fromString(threadIdx)).orElseThrow(() -> new BaseException(BaseErrorCode.THREAD_NOT_EXIST));

        likeJpaRepository.deleteAllByThreadId(UUID.fromString(threadIdx));
        imageJpaRepository.deleteAllByThreadIdx(UUID.fromString(threadIdx));
        threadJpaRepository.delete(entity);

        return new DeleteThreadRes(true);
    }

    /**
     * 메인 리스트 조회
     * */
    @Override
    public List<Thread> getThreadListOrderByTime() {
        List<ThreadEntity> entities = threadJpaRepository.findAllOrderByCreatedAt();
        return mapper.entitiesToThreads(entities);
    }

    @Override
    public List<Thread> getThreadListOrderByLike() {
        List<ThreadEntity> entities = threadJpaRepository.findAllByStatusOrderByLikes(ThreadStatus.ACTIVE);
        System.out.println(entities);
        return mapper.entitiesToThreads(entities);
    }

    /**
     * 스레드 팀 리스트 조회
     * */
    @Override
    public List<Thread> getThreadTeamList(Long teamIdx) throws BaseException{
        List<ThreadEntity> entities = threadJpaRepository.findAllByTeamIdx(teamIdx)
                .orElseThrow(() -> new BaseException(BaseErrorCode.TEAM_THREAD_NOT_EXIST));

        return mapper.entitiesToThreads(entities);
    }

    @Override
    public Thread getThread(String threadIdx) {
        ThreadEntity entity = threadJpaRepository.findById(UUID.fromString(threadIdx))
                .orElseThrow(() -> new BaseException(BaseErrorCode.THREAD_NOT_EXIST));

        return mapper.entityToThread(entity);

    }

    @Override
    public int findCommentNumber(UUID uuid) {
        System.out.println("gg");
        return threadJpaRepository.findCommentNumber(uuid);
    }

    /**
     * 댓글 리스트 조회
     * */
    @Override
    public List<Thread> getCommentList(String threadIdx) throws BaseException{
        List<ThreadEntity> threadEntities = threadJpaRepository.findThreadEntitiesByParentIdx(threadIdx)
                .orElseThrow(() -> new BaseException(BaseErrorCode.COMMENT_NOT_EXIST));

        return mapper.entitiesToThreads(threadEntities);
    }



    /**
     * 스레드, 댓글 좋아요 생성
     * */
    @Override
    public void createLike(Like like) {

        LikeEntity likeEntity = mapper.likeToEntity(like);

        likeJpaRepository.save(likeEntity);
        likeJpaRepository.flush();

    }

    /**
     * 스레드, 댓글 좋아요 삭제
     * */
    @Override
    public void deleteLike(Like like) {
        likeJpaRepository.deleteByThreadAndMember(UUID.fromString(like.getTargetThreadIdx()), UUID.fromString(like.getMemberIdx()));

        likeJpaRepository.flush();
    }



}
