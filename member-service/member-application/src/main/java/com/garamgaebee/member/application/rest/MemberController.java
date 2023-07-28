package com.garamgaebee.member.application.rest;


import com.garamgeabee.member.domain.dto.DeleteMemberResponse;
import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.ports.in.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{memberIdx}")
    public ResponseEntity<?> getMember(@PathVariable String memberIdx){
        GetMemberResponse getMemberResponse = memberService.getMember(UUID.fromString(memberIdx));

        return ResponseEntity.ok(getMemberResponse);
    }

    @PostMapping("/{memberIdx}")
    public ResponseEntity<?> deleteMember(@PathVariable String memberIdx){
        DeleteMemberResponse deleteMemberResponse = memberService.deleteMember(UUID.fromString(memberIdx));

        return ResponseEntity.ok(deleteMemberResponse);
    }
}
