package com.garamgaebee.thread.domain;

import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.thread.domain.dto.*;
import com.garamgaebee.thread.domain.entity.Like;
import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.entity.ThreadType;
import com.garamgaebee.thread.domain.mapper.ThreadDomainMapper;
import com.garamgaebee.thread.domain.ports.in.ThreadService;
import com.garamgaebee.thread.domain.ports.out.ImageFeignPublisher;
import com.garamgaebee.thread.domain.ports.out.MemberFeignPublisher;
import com.garamgaebee.thread.domain.ports.out.TeamFeignPublisher;
import com.garamgaebee.thread.domain.ports.out.ThreadRepository;
import com.garamgaebee.thread.domain.valueobject.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {

    private final ThreadRepository threadRepository;
    private final CreateThreadHelper createThreadHelper;
    private final ThreadDomainMapper mapper;

    private final MemberFeignPublisher memberFeignPublisher;

    private final ImageFeignPublisher imageFeignPublisher;

    private final TeamFeignPublisher teamFeign;

    @Autowired
    public ThreadServiceImpl(ThreadRepository threadRepository, CreateThreadHelper createThreadHelper, ThreadDomainMapper mapper, MemberFeignPublisher memberFeignPublisher, ImageFeignPublisher imageFeignPublisher, TeamFeignPublisher teamFeign) {
        this.threadRepository = threadRepository;
        this.createThreadHelper = createThreadHelper;
        this.mapper = mapper;
        this.memberFeignPublisher = memberFeignPublisher;
        this.imageFeignPublisher = imageFeignPublisher;
        this.teamFeign = teamFeign;
    }

    /**
     * 개인 스레드 작성 API - Service
     * */
    @Override
    //트랜잭션으로 묶은 이유: 안 묶으면 이미지가 안들어갑니다. 물론 이미지 레포지토리를 써서 넣을 순 있지만 코드가 굉장히 더러워지기 때문에
    //영속성 컨텍스트를 이용하기 위하여 트랜잭션으로 묶었습니다.
    @Transactional
    public CreateThreadRes createThread(List<MultipartFile> fileList, CreateThreadCommand req) throws BaseException{

        List<String> imgUrls = createThreadHelper.getImageList(fileList);

        //TODO jwt에서 userIdx 가져오기
        String authorIdx = req.getAuthorIdx();

        MemberVO member = memberFeignPublisher.getFeignMember(authorIdx);
        String memberProfileUrl = member.getProfileImgUrl();
        String authorName = member.getMemberName();

        Thread createTarget = Thread.builder()
                .authorIdx(authorIdx)
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
                .authorIdx(created.getAuthorIdx())
                .isComment(Boolean.FALSE)
                .authorName(created.getAuthorName())
                .department(member.getDept())
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
        List<String> imgUrls = createThreadHelper.getImageList(fileList);

        //TODO jwt에서 userIdx 가져오기
        String authorIdx = req.getAuthorIdx();

        MemberVO member = memberFeignPublisher.getFeignMember(authorIdx);
        String memberProfileUrl = member.getProfileImgUrl();
        String authorName = member.getMemberName();
        //TODO teamIdx 가지고 Team 객체 가져오기
        String teamProfileUrl = "test";
        String teamName = "teamName";

        Thread createTarget = Thread.builder()
                .authorIdx(authorIdx)
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
                .authorIdx(created.getAuthorIdx())
                .isComment(Boolean.FALSE)
                .authorName(created.getAuthorName())
                .department(member.getDept())
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
    @Transactional(rollbackFor = BaseException.class)
    public DeleteThreadRes deleteThread(String threadIdx) throws BaseException{
        //이미지도 삭제하기
        Thread thread = threadRepository.getThread(threadIdx);

        if(!thread.getImgUrls().isEmpty()){
            deleteImages(thread.getImgUrls());
        }

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

        for(GetThreadListRes thread : res) {
            if(!thread.getIsComment()) {
                int commentNumber = threadRepository.findCommentNumber(UUID.fromString(thread.getThreadId()));
                thread.setCommentNumber(commentNumber);
            }
            else{
                thread.setCommentNumber(-1);
            }
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

        for(GetThreadListRes thread : res) {
            if(!thread.getIsComment()) {
                int commentNumber = threadRepository.findCommentNumber(UUID.fromString(thread.getThreadId()));
                thread.setCommentNumber(commentNumber);
            }
            else{
                thread.setCommentNumber(-1);
            }
        }

        return res;
    }

    /**
     * Feign Team Thread List 조회
     * */
    @Override
    public List<GetFeignTeamThreadsRes> getTeamThreads(Long teamIdx) {
        List<Thread> threads = threadRepository.getThreadTeamList(teamIdx);

        List<GetFeignTeamThreadsRes> res = new ArrayList<>();

        for (Thread thread : threads) {
            res.add(mapper.ThreadsToFeignList(thread));
        }

        return res;
    }

    /**
     * 댓글 생성 (개인) - Service
     * */
    @Override
    @Transactional
    public CreateCommentRes createComment(List<MultipartFile> fileList, CreateCommentCommand req) throws BaseException{
        List<String> imgUrls = createThreadHelper.getImageList(fileList);

        //TODO jwt에서 memberIdx 가져오기
        String authorIdx = req.getAuthorIdx();

        MemberVO member = memberFeignPublisher.getFeignMember(authorIdx);
        String memberProfileUrl = member.getProfileImgUrl();
        String authorName = member.getMemberName();

        Thread thread = Thread.builder()
                .authorIdx(authorIdx)
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
                .authorIdx(created.getAuthorIdx())
                .isComment(Boolean.TRUE)
                .rootThreadIdx(created.getParentIdx())
                .authorName(authorName)
                .department(member.getDept())
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
        List<String> imgUrls = createThreadHelper.getImageList(fileList);

        //TODO jwt에서 userIdx 가져오기
        String authorIdx = req.getAuthorIdx();

        MemberVO member = memberFeignPublisher.getFeignMember(authorIdx);
        String memberProfileUrl = member.getProfileImgUrl();
        String authorName = member.getMemberName();
        //TODO teamIdx 가지고 TeamVO 객체 가져오기
        String teamProfileUrl = "test";
        String teamName = "teamName";

        Thread thread = Thread.builder()
                .authorIdx(authorIdx)
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
                .authorIdx(created.getAuthorIdx())
                .isComment(Boolean.TRUE)
                .rootThreadIdx(created.getParentIdx())
                .authorName(created.getAuthorName())
                .department(member.getDept())
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
    public CreateLikeRes createLike(CreateLikeCommand req) {
        //TODO jwt에서 userIdx 가져오기
        String memberIdx = req.getMemberIdx();

        Like like = Like.builder()
                .targetThreadIdx(req.getThreadIdx())
                .memberIdx(memberIdx)
                .build();

        threadRepository.createLike(like);

        return CreateLikeRes.builder()
                .likeSuccess(Boolean.TRUE)
                .targetThreadIdx(req.getThreadIdx())
                .build();

    }

    /**
     * 스레드, 팀 좋아요 삭제 - Service
     * */
    @Override
    @Transactional
    public DeleteLikeRes deleteLike(DeleteLikeCommand req) {
        //TODO jwt에서 userIdx 가져오기
        String memberIdx = req.getMemberIdx();

        Like like = Like.builder()
                .targetThreadIdx(req.getThreadIdx())
                .memberIdx(memberIdx)
                .build();

        threadRepository.deleteLike(like);

        return DeleteLikeRes.builder()
                .deleteSuccess(Boolean.TRUE)
                .targetThreadId(req.getThreadIdx())
                .build();
    }

    private void deleteImages(List<String> urls){
        DeleteImageCommand deleteImageCommand = new DeleteImageCommand(urls);

        imageFeignPublisher.deleteFeignImages(deleteImageCommand);
    }
}
