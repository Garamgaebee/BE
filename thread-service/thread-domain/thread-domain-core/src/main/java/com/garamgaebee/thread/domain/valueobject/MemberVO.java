package com.garamgaebee.thread.domain.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberVO {
    private UUID memberIdx;

    private String memberName;

    private String nickname;

    private String company;

    private String duty;

    private String level;

    private String profileImgUrl;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String status;

    private List<CareerVO> career;

    private List<String> email;

    private List<String> sns;
}
