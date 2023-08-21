package com.garamgaebee.thread.domain;

import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.thread.domain.dto.*;
import com.garamgaebee.thread.domain.entity.Like;
import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.entity.ThreadType;
import com.garamgaebee.thread.domain.mapper.ThreadDomainMapper;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@ComponentScan
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final CreateThreadHelper createThreadHelper;
    private final ThreadDomainMapper mapper;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository, CreateThreadHelper createThreadHelper, ThreadDomainMapper mapper) {
        this.threadRepository = threadRepository;
        this.createThreadHelper = createThreadHelper;
        this.mapper = mapper;
    }

    /**
     * 개인 스레드 작성 API - Service
     * */
    @Override
    //트랜잭션으로 묶은 이유: 안 묶으면 이미지가 안들어갑니다. 물론 이미지 레포지토리를 써서 넣을 순 있지만 코드가 굉장히 더러워지기 때문에
    //영속성 컨텍스트를 이용하기 위하여 트랜잭션으로 묶었습니다.
    @Transactional
    public CreateThreadRes createThread(List<MultipartFile> fileList, CreateThreadCommand req) throws BaseException{
        //TODO 이미지 url로 바꾸기
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("test");
        //TODO jwt에서 userIdx 가져오기
        //TODO memberIdx 가지고 memberImgUrl 가져오기
        String memberProfileUrl = "test";
        String authorName = "test";

        Thread createTarget = Thread.builder()
                .authorIdx(req.getAuthorIdx())
                .authorName(authorName)
                .content(req.getContent())
                .isComment(req.isComment())
                .parentIdx("NONE")
                .imgUrls(imgUrls)
                .memberProfileImg(memberProfileUrl)
                .teamProfileImg("NONE")
                .teamName("NONE")
                .type(ThreadType.PERSONAL)
                .teamId(-1L)
                .build();

        Thread created = createThreadHelper.createThread(createTarget);


        return CreateThreadRes.builder()
                .threadId(created.getThreadIdx())
                .isComment(Boolean.FALSE)
                .authorName(created.getAuthorName())
                .teamName("NONE")
                .authorImgUrl(memberProfileUrl)
                .teamImgUrl("NONE")
                .imgs(created.getImgUrls())
                .content(created.getContent())
                .likeNumber(created.getLikeNumber())
                .commentNumber(created.getCommentNumber())
                .createdAt(created.getCreatedAt().toString())
                .type(created.getType().toString())
                .build();
    }

    /**
     * 팀 스레드 생성 API - Service
     * */
    @Override
    //트랜잭션으로 묶은 이유: 안 묶으면 이미지가 안들어갑니다. 물론 이미지 레포지토리를 써서 넣을 순 있지만 코드가 굉장히 더러워지기 때문에
    //영속성 컨텍스트를 이용하기 위하여 트랜잭션으로 묶었습니다.
    @Transactional
    public CreateThreadRes createTeamThread(List<MultipartFile> fileList, CreateThreadCommand req, Long teamId) throws BaseException {
        //TODO 이미지 url로 바꾸기
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("test");
        //TODO jwt에서 userIdx 가져오기
        //TODO memberIdx 가지고 memberImgUrl과 memberName 가져오기
        String memberProfileUrl = "test";
        String authorName = "memberName";
        //TODO teamIdx 가지고 Team 객체 가져오기
        String teamProfileUrl = "test";
        String teamName = "teamName";

        //대상 엔티티 만들기
        Thread createTarget = Thread.builder()
                .authorIdx(req.getAuthorIdx())
                .content(req.getContent())
                .isComment(req.isComment())
                .parentIdx("NONE")
                .imgUrls(imgUrls)
                .memberProfileImg(memberProfileUrl)
                .authorName(authorName)
                .teamProfileImg(teamProfileUrl)
                .teamName(teamName)
                .type(ThreadType.TEAM)
                .teamId(teamId)
                .build();

        Thread created = createThreadHelper.createThread(createTarget);

        return CreateThreadRes.builder()
                .threadId(created.getThreadIdx())
                .isComment(Boolean.FALSE)
                .authorName(created.getAuthorName())
                .teamName(created.getTeamName())
                .authorImgUrl(memberProfileUrl)
                .teamImgUrl(teamProfileUrl)
                .imgs(created.getImgUrls())
                .content(created.getContent())
                .likeNumber(created.getLikeNumber())
                .commentNumber(created.getCommentNumber())
                .createdAt(created.getCreatedAt().toString())
                .type(created.getType().toString())
                .build();
    }

    /**
     * 스레드 삭제 API - Service
     * */
    @Override
    @Transactional
    public DeleteThreadRes deleteThread(String threadIdx) throws BaseException{
        return threadRepository.deleteThread(threadIdx);

    }

    /**
     * 스레드 메인 리스트 조회 - Service
     * */
    @Override
    @Transactional
    public List<GetThreadListRes> getThreadList(Integer orderType) {
        List<Thread> threads;

        if (orderType == 0) {
            threads = threadRepository.getThreadListOrderByTime();
        } else {
            threads = threadRepository.getThreadListOrderByLike();
        }

        List<GetThreadListRes> res = new ArrayList<>();

        for (Thread thread : threads) {
            res.add(mapper.ThreadListToDtoList(thread));
        }

        return res;
    }

    /**
     * 스레드 팀 리스트 조회
     * */
    @Override
    @Transactional
    public List<GetThreadListRes> getThreadTeamList(Long teamIdx) throws BaseException{
        List<Thread> threads = threadRepository.getThreadTeamList(teamIdx);

        List<GetThreadListRes> res = new ArrayList<>();

        for (Thread thread : threads) {
            res.add(mapper.ThreadListToDtoList(thread));
        }

        return res;
    }

    /**
     * 댓글 생성 (개인) - Service
     * */
    @Override
    @Transactional
    public CreateCommentRes createComment(List<MultipartFile> fileList, CreateCommentCommand req) throws BaseException{
        //TODO 이미지 url로 바꾸기
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("test");
        //TODO jwt에서 userIdx 가져오기
        //TODO memberIdx 가지고 memberImgUrl 가져오기
        String memberProfileUrl = "test";
        String authorName = "test";

        //대상 엔티티 만들기
        Thread thread = Thread.builder()
                .authorIdx(req.getAuthorIdx())
                .authorName(authorName)
                .content(req.getContent())
                .likeNumber(0)
                .isComment(true)
                .teamId(-1L)
                .teamName("NONE")
                .parentIdx(req.getRootThreadIdx())
                .imgUrls(imgUrls)
                .memberProfileImg(memberProfileUrl)
                .teamProfileImg("NONE")
                .type(ThreadType.PERSONAL)
                .build();

        Thread created = createThreadHelper.createThread(thread);

        return CreateCommentRes.builder()
                .threadId(created.getThreadIdx())
                .isComment(Boolean.TRUE)
                .rootThreadIdx(created.getParentIdx())
                .authorName(authorName)
                .teamName("NONE")
                .authorImgUrl(memberProfileUrl)
                .teamImgUrl("NONE")
                .teamName(created.getTeamName())
                .imgs(created.getImgUrls())
                .content(created.getContent())
                .likeNumber(created.getLikeNumber())
                .commentNumber(created.getCommentNumber())
                .createdAt(created.getCreatedAt().toString())
                .type(created.getType().toString())
                .build();
    }

    /**
     * 댓글 생성 (팀) - Service
     * */
    @Override
    @Transactional
    public CreateCommentRes createTeamComment(List<MultipartFile> fileList, CreateCommentCommand req, Long teamId) {
        //TODO 이미지 url로 바꾸기
        List<String> imgUrls = new ArrayList<>();
        imgUrls.add("test");
        //TODO jwt에서 userIdx 가져오기
        //TODO memberIdx 가지고 memberImgUrl 가져오기
        String memberProfileUrl = "test";
        String authorName = "test";
        //TODO teamIdx 가지고 Team 객체 가져오기
        String teamProfileUrl = "test";
        String teamName = "teamName";

        //대상 엔티티 만들기
        Thread thread = Thread.builder()
                .authorIdx(req.getAuthorIdx())
                .authorName(authorName)
                .content(req.getContent())
                .likeNumber(0)
                .isComment(true)
                .teamId(teamId)
                .teamName(teamName)
                .parentIdx(req.getRootThreadIdx())
                .imgUrls(imgUrls)
                .memberProfileImg(memberProfileUrl)
                .teamProfileImg(teamProfileUrl)
                .type(ThreadType.TEAM)
                .build();

        Thread created = createThreadHelper.createThread(thread);


        return CreateCommentRes.builder()
                .threadId(created.getThreadIdx())
                .isComment(Boolean.TRUE)
                .rootThreadIdx(created.getParentIdx())
                .authorName(created.getAuthorName())
                //TODO 팀 객체에서 프로필 이미지 가져오기
                .teamName(created.getTeamName())
                .authorImgUrl(memberProfileUrl)
                .teamImgUrl(teamProfileUrl)
                .imgs(created.getImgUrls())
                .content(created.getContent())
                .likeNumber(created.getLikeNumber())
                .commentNumber(created.getCommentNumber())
                .createdAt(created.getCreatedAt().toString())
                .type(created.getType().toString())
                .build();
    }

    /**
     * 댓글 리스트 조회
     * */
    @Override
    @Transactional
    public List<GetCommentRes> getCommentList(String threadIdx) throws BaseException{
        List<Thread> commentList = threadRepository.getCommentList(threadIdx);

        return mapper.commentListToDtoList(commentList);
    }

    /**
     * 스레드, 팀 좋아요 생성 - Service
     * */
    @Override
    @Transactional
    public CreateLikeRes createLike(String threadIdx) {
        //TODO jwt에서 userIdx 가져오기
        String memberIdx = "test";

        Like like = Like.builder()
                .targetThreadIdx(threadIdx)
                .memberIdx(memberIdx)
                .build();

        threadRepository.createLike(like);

        return CreateLikeRes.builder()
                .likeSuccess(Boolean.TRUE)
                .targetThreadIdx(threadIdx)
                .build();

    }

    /**
     * 스레드, 팀 좋아요 삭제 - Service
     * */
    @Override
    @Transactional
    public DeleteLikeRes deleteLike(String threadIdx) {
        //TODO jwt에서 userIdx 가져오기
        String memberIdx = "test";

        Like like = Like.builder()
                .targetThreadIdx(threadIdx)
                .memberIdx(memberIdx)
                .build();

        threadRepository.deleteLike(like);

        return DeleteLikeRes.builder()
                .deleteSuccess(Boolean.TRUE)
                .targetThreadId(threadIdx)
                .build();
    }


}
