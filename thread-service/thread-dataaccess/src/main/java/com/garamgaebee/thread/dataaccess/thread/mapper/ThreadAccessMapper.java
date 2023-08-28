package com.garamgaebee.thread.dataaccess.thread.mapper;

import com.garamgaebee.thread.dataaccess.thread.entity.LikeEntity;
import com.garamgaebee.thread.dataaccess.thread.entity.ThreadEntity;
import com.garamgaebee.thread.dataaccess.thread.entity.ThreadImageEntity;
import com.garamgaebee.thread.domain.entity.Like;
import com.garamgaebee.thread.domain.entity.Thread;
import com.garamgaebee.thread.domain.entity.ThreadStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ThreadAccessMapper {
    /**
     * 스레드 생성 시 사용하는 매퍼
     * */
    public ThreadEntity threadToEntity(Thread createTarget) {
        return ThreadEntity.builder()
                .authorIdx(UUID.fromString(createTarget.getAuthorIdx()))
                .authorName(createTarget.getAuthorName())
                .authorImageUrl(createTarget.getMemberProfileImg())
                .threadType(createTarget.getType())
                .teamIdx(createTarget.getTeamId())
                .teamName(createTarget.getTeamName())
                .teamImageUrl(createTarget.getTeamProfileImg())
                .content(createTarget.getContent())
                .isComment(createTarget.isComment())
                .parentIdx(createTarget.getParentIdx())
                .build();

    }

    /**
     * 엔티티를 Thread 객체로 바꿔주는 매퍼
     * */
    public Thread entityToThread(ThreadEntity entity){

        return Thread.builder()
                .threadIdx(entity.getThreadIdx().toString())
                .authorIdx(entity.getAuthorIdx().toString())
                .authorName(entity.getAuthorName())
                .content(entity.getContent())
                .likeNumber(entity.getLikes().size())
                .isComment(entity.isComment())
                .teamId(entity.getTeamIdx())
                .teamName(entity.getTeamName())
                .parentIdx(entity.getParentIdx())
                .imgUrls(entityToImage(entity.getImages()))
                .memberProfileImg(entity.getAuthorImageUrl())
                .teamProfileImg(entity.getTeamImageUrl())
                .type(entity.getThreadType())
                .createdAt(entity.getCreatedAt())
                .build();

    }

    /**
     * 스레드 엔티티 리스트를 스레드 리스트로 매핑하는 매퍼
     * */
    public List<Thread> entitiesToThreads(List<ThreadEntity> threadEntities) {
        List<Thread> threads = new ArrayList<>();

        for (ThreadEntity entity : threadEntities) {
            threads.add(entityToThread(entity));
        }

        return threads;
    }

    /**
     * 이미지 리스트 매퍼
     * */
    public List<ThreadImageEntity> imageToEntity(List<String> urls, UUID threadIdx){
        if(urls == null) return new ArrayList<>();

        List<ThreadImageEntity> imageList = new ArrayList<>();

        for (String url : urls) {
            imageList.add(
                    ThreadImageEntity.builder()
                            .threadIdx(threadIdx)
                            .url(url)
                            .status(ThreadStatus.ACTIVE)
                            .build()
            );
        }

        return imageList;
    }

    /**
     * 이미지 url 뽑아내기
     * */
    private List<String> entityToImage(List<ThreadImageEntity> entities){

        if(entities.isEmpty()) return new ArrayList<>();

        List<String> urls = new ArrayList<>();

        for (ThreadImageEntity entity : entities) {
            urls.add(entity.getUrl());
        }

        return urls;
    }

    /**
     * 좋아요 매퍼
     * */
    public LikeEntity likeToEntity(Like like) {
        return LikeEntity.builder()
                .targetThreadIdx(UUID.fromString(like.getTargetThreadIdx()))
                .memberIdx(UUID.fromString(like.getMemberIdx()))
                .build();
    }


}
