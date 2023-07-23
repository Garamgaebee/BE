package com.garamgaebee.memberserivce.application.rest;

import com.garamgaebee.memberserivce.domain.dto.DeleteMemberCommand;
import com.garamgaebee.memberserivce.domain.dto.DeleteMemberResponse;
import com.garamgaebee.memberserivce.domain.dto.GetMemberResponse;
import com.garamgaebee.memberserivce.domain.ports.in.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{memberIdx}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable Long memberIdx){
        GetMemberResponse getMemberResponse = memberService.getMember(memberIdx);

        return ResponseEntity.ok(getMemberResponse);
    }

    @PostMapping("/{memberIdx}")
    public ResponseEntity<DeleteMemberResponse> deleteMember(@PathVariable Long memberIdx){
        DeleteMemberResponse deleteMemberResponse = memberService.deleteMember(memberIdx);

        return ResponseEntity.ok(deleteMemberResponse);
    }
}
