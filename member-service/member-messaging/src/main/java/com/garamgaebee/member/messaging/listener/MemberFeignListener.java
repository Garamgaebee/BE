package com.garamgaebee.member.messaging.listener;

import com.garamgeabee.member.domain.dto.CreateMemberCommand;
import com.garamgeabee.member.domain.dto.GetFeignMemberResponse;
import com.garamgeabee.member.domain.ports.in.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/feign/member")
public class MemberFeignListener {
    private final MemberService memberService;

    @Autowired
    public MemberFeignListener(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 멤버 생성
     * */
    @PostMapping
    public UUID createMember(@RequestBody CreateMemberCommand req){
        return memberService.createMember(req);
    }

    /**
     * 회원 닉네임 중복 확인
     * */
    @GetMapping("/duplicate")
    public boolean checkDuplicateNickname(@RequestParam("nickname") String nickname){
        return memberService.checkDuplicateNickname(nickname);
    }

    /**
     * 회원 정보 Feign 조회
     * */
    @GetMapping
    public GetFeignMemberResponse getFeignMember(@RequestParam("user-idx") String userIdx){
        return memberService.getFeignMember(userIdx);
    }
}
