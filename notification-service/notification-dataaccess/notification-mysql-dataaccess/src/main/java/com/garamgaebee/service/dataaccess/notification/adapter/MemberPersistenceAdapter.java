package com.garamgaebee.service.dataaccess.notification.adapter;

import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.notification.service.domain.entity.FcmToken;
import com.garamgaebee.notification.service.domain.entity.Member;
import com.garamgaebee.notification.service.domain.port.output.persistence.*;
import com.garamgaebee.service.dataaccess.notification.entity.FcmTokenEntity;
import com.garamgaebee.service.dataaccess.notification.entity.MemberEntity;
import com.garamgaebee.service.dataaccess.notification.repository.FcmTokenRepository;
import com.garamgaebee.service.dataaccess.notification.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements
        DeleteFcmTokenPort, DeleteMemberPort, LoadMemberPort,
        SaveMemberPort, UpdateMemberStatePort
{

    private final FcmTokenRepository fcmTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void deleteFcmTokenListByTokenValue(List<String> tokenList) {
        fcmTokenRepository.deleteAllByFcmTokenValue(tokenList);
    }

    @Transactional
    @Override
    public void deleteSingleFcmTokenByTokenValue(String token) {
        fcmTokenRepository.deleteByFcmToken(token);
    }

    @Override
    public void deleteFcmTokenListBeforeTime(LocalDateTime time) {
        fcmTokenRepository.deleteAllByTimeBefore(time);
    }

    @Transactional
    @Override
    public void deleteMemberByOwner(UUID ownerId) {
        memberRepository.deleteByOwnerId(ownerId);
    }

    @Override
    public Optional<Member> loadMemberByOwner(UUID ownerId) {
        Optional<MemberEntity> memberEntity = memberRepository.findByOwnerId(ownerId);

        if(memberEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(memberEntity.get().ofDomainEntity());
    }

    @Override
    public List<Member> loadAllMembers() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();

        return memberEntityList.stream().map(memberEntity -> {
            return memberEntity.ofDomainEntity();
        }).collect(Collectors.toList());
    }

    @Override
    public List<Member> loadMembersByOwners(List<UUID> ownerId) {
        List<MemberEntity> memberEntityList = memberRepository.findByOwnerIdList(ownerId);

        return memberEntityList.stream().map(memberEntity -> {
            return memberEntity.ofDomainEntity();
        }).collect(Collectors.toList());
    }

    @Override
    public Member updateMemberSetting(Member member) {
        MemberEntity memberEntity = memberRepository.findById(member.getId()).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        memberEntity.setPushSetting(member.getPushSetting());
        return memberRepository.save(memberEntity).ofDomainEntity();
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(
                MemberEntity.builder()
                        .id(member.getId())
                        .ownerId(member.getOwnerId())
                        .isPushNewFunctionEvent(member.getPushSetting().getIsPushNewFunctionEvent())
                        .isPushTeamEvent(member.getPushSetting().getIsPushTeamEvent())
                        .isPushThreadEvent(member.getPushSetting().getIsPushThreadEvent())
                        .isPushHotThreadEvent(member.getPushSetting().getIsPushHotThreadEvent())
                        .memberNotificationEntityList(new ArrayList<>())
                        .fcmTokenEntityList(new ArrayList<>())
                .build())
                .ofDomainEntity();
    }

    @Override
    public Member updateMemberFcmTokens(Member member) {
        MemberEntity memberEntity = memberRepository.findById(member.getId()).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        for(FcmToken fcmToken : member.getFcmTokenList()) {
            if(fcmToken.getId() == null) {
                memberEntity.addFcmTokenEntity(
                        FcmTokenEntity.builder()
                                .id(fcmToken.getId())
                                .fcmToken(fcmToken.getFcmToken())
                                .time(fcmToken.getTime())
                        .build());
            }
            else {
                for(FcmTokenEntity fcmTokenEntity : memberEntity.getFcmTokenEntityList()) {
                    if(fcmTokenEntity.getId() == fcmToken.getId()) {
                        fcmTokenEntity.setTime(fcmToken.getTime());
                    }
                }
            }
        }

        return memberRepository.save(memberEntity).ofDomainEntity();
    }
}
