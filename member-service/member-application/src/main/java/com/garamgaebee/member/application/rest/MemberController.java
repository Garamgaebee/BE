package com.garamgaebee.member.application.rest;


import com.garamgaebee.common.exception.BaseException;
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
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable String memberIdx) throws BaseException {
        GetMemberResponse getMemberResponse = memberService.getMember(UUID.fromString(memberIdx));

        return ResponseEntity.ok(getMemberResponse);
    }

    @DeleteMapping("/{memberIdx}")
    public ResponseEntity<DeleteMemberResponse> deleteMember(@PathVariable String memberIdx) throws BaseException{
        DeleteMemberResponse deleteMemberResponse = memberService.deleteMember(UUID.fromString(memberIdx));

        return ResponseEntity.ok(deleteMemberResponse);
    }
}
