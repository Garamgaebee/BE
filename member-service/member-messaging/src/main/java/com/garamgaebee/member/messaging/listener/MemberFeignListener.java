package com.garamgaebee.member.messaging.listener;

import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.member.domain.dto.CreateMemberCommand;
import com.garamgaebee.member.domain.dto.DeleteMemberResponse;
import com.garamgaebee.member.domain.dto.GetFeignMemberResponse;
import com.garamgaebee.member.domain.ports.in.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
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
        GetFeignMemberResponse res = memberService.getFeignMember(userIdx);

        log.info(res.toString());
        return res;
    }

    /**
     * 멤버 삭제 (회원 탈퇴)
     * */
    @DeleteMapping("/{member-idx}")
    public DeleteMemberResponse deleteMember(@PathVariable("member-idx") String memberIdx) throws BaseException {
        return memberService.deleteMember(UUID.fromString(memberIdx));


    }

    /**
     * 팀에 속한 회원 리스트
     * */
    @GetMapping("/team-list")
    public List<GetFeignMemberResponse> getTeamMembers(@RequestParam("members") List<String> members) {
        return memberService.getTeamMembers(members);
    }
}
