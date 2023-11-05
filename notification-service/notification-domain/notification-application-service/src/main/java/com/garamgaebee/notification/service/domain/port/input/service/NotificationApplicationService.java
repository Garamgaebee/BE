package com.garamgaebee.notification.service.domain.port.input.service;

import com.garamgaebee.notification.service.domain.dto.*;

import java.util.List;
import java.util.UUID;

public interface NotificationApplicationService {

    // 멤버 알림설정 최초 등록
    public Boolean registerNewNotification(RegisterNotificationCommand registerNotificationCommand);

    // 알림 설정 리스트 조회
    public GetNotificationResponse getNotificationSettingInfo(UUID memberId);

    // 이벤트 및 기능 추가 알림 설정 변경
    public Boolean changePushNewEventNotificationStatus(UUID memberId);
    // Team 관련 알림 설정 변경
    public Boolean changePushTeamEventNotificationStatus(UUID memberId);
    // Thread 관련 알림 설정 변경
    public Boolean changePushThreadEventNotificationStatus(UUID memberId);
    // 인기 Thread 관련 알림 설정 변경
    public Boolean changePushHotThreadEventNotificationStatus(UUID memberId);

    // 신기능 알림 생성
    public Boolean createNewNotificationDetail(CreateNewNotificationCommand createNewNotificationCommand);
    // 팀 알림 생성
    public Boolean createTeamNotificationDetail(CreateTeamNotificationCommand createTeamNotificationCommand);
    // thread 알림 생성
    public Boolean createThreadNotificationDetail(CreateThreadNotificationCommand createThreadNotificationCommand);
    // hot thread 알림 생성
    public Boolean createHotThreadNotificationDetail(CreateHotThreadNotificationCommand createHotThreadNotificationCommand);

    // member 알림 목록 조회
    public List<GetMemberNotificationResponse> getMemberNotificationList(UUID memberId);
    // 알림 읽음 처리
    public Boolean readMemberNotification(Long memberNotificationId);

}
