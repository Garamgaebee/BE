package com.garamgaebee.member.application.rest;


import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.member.domain.dto.DeleteMemberResponse;
import com.garamgaebee.member.domain.dto.GetMemberResponse;
import com.garamgaebee.member.domain.dto.ProfileImgResponse;
import com.garamgaebee.member.domain.ports.in.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 멤버 조회
     * */
    @GetMapping("/{member-idx}")
    public ResponseEntity<GetMemberResponse> getMember(@PathVariable("member-idx") String memberIdx) throws BaseException {
        GetMemberResponse getMemberResponse = memberService.getMember(UUID.fromString(memberIdx));

        return ResponseEntity.ok(getMemberResponse);
    }



    /**
     * 프로필 이미지 등록
     * */
    @PostMapping(value = "/{member-idx}/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProfileImgResponse> postProfileImg(@PathVariable("member-idx") String memberIdx, @RequestPart(value = "file") MultipartFile file) {
        ProfileImgResponse profileImgResponse = memberService.postProfileImg(memberIdx, file);

        return ResponseEntity.ok(profileImgResponse);
    }

    /**
     * 프로필 이미지 수정
     * */
    @PostMapping(value = "/{member-idx}/image/modify", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProfileImgResponse> patchProfileImg(@PathVariable("member-idx") String memberIdx, @RequestPart(value = "file") MultipartFile file) throws BaseException{
        ProfileImgResponse profileImgResponse = memberService.patchProfileImg(memberIdx, file);

        return ResponseEntity.ok(profileImgResponse);
    }
}
