package com.garamgaebee.member.messaging.listener;

import com.garamgeabee.member.domain.dto.PatchMemberImgCommand;
import com.garamgeabee.member.domain.ports.in.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/feign/member")
public class MemberFeignListener {

    //TODO: 유저 등록

    private final MemberService memberService;

    @Autowired
    public MemberFeignListener(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/{memberIdx}")
    public UUID createMember(@PathVariable UUID memberIdx){
        return memberService.createMember(memberIdx);
    }

    @PatchMapping("/image")
    public boolean insertMemberImage(@RequestBody PatchMemberImgCommand req){
        return memberService.insertMemberImage(req);
    }
}
