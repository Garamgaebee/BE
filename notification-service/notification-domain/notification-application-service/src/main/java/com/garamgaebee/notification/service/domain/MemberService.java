package com.garamgaebee.notification.service.domain;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.notification.service.domain.entity.Member;
import com.garamgaebee.notification.service.domain.port.input.command.RegisterMemberCommand;
import com.garamgaebee.notification.service.domain.port.input.response.GetNotificationResponse;
import com.garamgaebee.notification.service.domain.port.input.*;
import com.garamgaebee.notification.service.domain.port.output.persistance.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService implements
        ChangePushSettingUseCase, CreateMemberUseCase, DeleteFcmTokenListUseCase,
        DeleteMemberUseCase, DeleteSingleFcmTokenUseCase, GetPushSettingUseCase {

    private final LoadMemberPort loadMemberPort;
    private final SaveMemberPort saveMemberPort;
    private final DeleteMemberPort deleteMemberPort;
    private final UpdateMemberStatePort updateMemberStatePort;
    private final DeleteFcmTokenPort deleteFcmTokenPort;

    /**
     * 신기능 알림 설정 변경
     */
    @Override
    public Boolean changePushNewEventNotificationStatus(UUID ownerId) {
        Member member = loadMemberPort.loadMemberByOwner(ownerId).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        // 신기능 알림 설정 변경
        member.changeNewFunctionPush();

        saveMemberPort.updateMemberSetting(member);

        return true;
    }

    /**
     * 팀 알림 설정 변경
     */
    @Override
    public Boolean changePushTeamEventNotificationStatus(UUID ownerId) {
        Member member = loadMemberPort.loadMemberByOwner(ownerId).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        // 팀 알림 설정 변경
        member.changeTeamPush();

        saveMemberPort.updateMemberSetting(member);

        return true;
    }

    /**
     * 스레드 알림 설정 변경
     */
    @Override
    public Boolean changePushThreadEventNotificationStatus(UUID ownerId) {
        Member member = loadMemberPort.loadMemberByOwner(ownerId).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        // 스레드 알림 설정 변경
        member.changeThreadPush();

        saveMemberPort.updateMemberSetting(member);

        return true;
    }

    /**
     * 인기 스레드 알림 설정 변경
     */
    @Override
    public Boolean changePushHotThreadEventNotificationStatus(UUID ownerId) {
        Member member = loadMemberPort.loadMemberByOwner(ownerId).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        // 인기 스레드 알림 설정 변경
        member.changeHotThreadPush();

        saveMemberPort.updateMemberSetting(member);

        return true;
    }

    /**
     * member 생성
     */
    @Override
    public Boolean createMember(RegisterMemberCommand registerMemberCommand) {
        // member 저장
        Member member = saveMemberPort.createMember(Member.create(registerMemberCommand.getOwnerId()));

        // fcm token 추가
        member.addFcmToken(FcmToken.create(
                registerMemberCommand.getFcmToken(), registerMemberCommand.getFcmTokenTime())
        );

        // member fcm token 저장
        updateMemberStatePort.updateMemberFcmTokens(member);

        return true;
    }

    /**
     * fcm token List로 삭제
     */
    @Override
    public void deleteFcmTokenList(List<String> fcmTokenList) {
        // delete fcm token list
        deleteFcmTokenPort.deleteFcmTokenListByTokenValue(fcmTokenList);
    }

    /**
     * 회원 삭제
     */
    @Override
    public void deleteMember(UUID ownerId) {
        // delete Member
        deleteMemberPort.deleteMemberByOwner(ownerId);

    }

    /**
     * 단일 fcm token 삭제
     */
    @Override
    public void deleteSingleFcmToken(String fcmToken) {
        deleteFcmTokenPort.deleteSingleFcmTokenByTokenValue(fcmToken);
    }

    /**
     * Member 알림 설정 조회
     */
    @Override
    public GetNotificationResponse getNotificationSettingInfo(UUID ownerId) {
        Member member = loadMemberPort.loadMemberByOwner(ownerId).orElseThrow(
                () -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST)
        );

        return GetNotificationResponse.builder()
                .id(member.getId())
                .event(member.getPushSetting().getIsPushNewFunctionEvent())
                .community(member.getPushSetting().getIsPushTeamEvent())
                .hotPosting(member.getPushSetting().getIsPushHotThreadEvent())
                .posting(member.getPushSetting().getIsPushThreadEvent())
                .build();
    }
}
