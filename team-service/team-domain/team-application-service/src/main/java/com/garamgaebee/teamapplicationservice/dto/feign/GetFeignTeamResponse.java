package com.garamgaebee.teamapplicationservice.dto.feign;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class GetFeignTeamResponse {
    String name;
    String introduce;
    String image;
    List<Member> memberList;
    List<String> externalLink;
    List<Notification> notificationList;
    @Getter
    @Builder
    public static class Notification{
        List<String> imageUrl;
        String content;
    }
    @Getter
    @Builder
    public static class Member{
        UUID memberId;
        String position;
    }
}