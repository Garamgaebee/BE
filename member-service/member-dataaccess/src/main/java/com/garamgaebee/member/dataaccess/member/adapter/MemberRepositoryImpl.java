package com.garamgaebee.member.dataaccess.member.adapter;


import com.garamgaebee.common.exception.BaseErrorCode;
import com.garamgaebee.common.exception.BaseException;
import com.garamgaebee.member.dataaccess.member.entity.MemberEntity;
import com.garamgaebee.member.dataaccess.member.mapper.MemberAccessMapper;
import com.garamgaebee.member.dataaccess.member.repository.CareerJpaRepository;
import com.garamgaebee.member.dataaccess.member.repository.EmailJpaRepository;
import com.garamgaebee.member.dataaccess.member.repository.MemberJpaRepository;
import com.garamgaebee.member.dataaccess.member.repository.SnsJpaRepository;
import com.garamgaebee.member.domain.entity.Member;
import com.garamgaebee.member.domain.ports.out.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final EmailJpaRepository emailJpaRepository;
    private final CareerJpaRepository careerJpaRepository;
    private final SnsJpaRepository snsJpaRepository;

    private final MemberAccessMapper memberAccessMapper;

    @Autowired
    public MemberRepositoryImpl(MemberJpaRepository memberJpaRepository, EmailJpaRepository emailJpaRepository, CareerJpaRepository careerJpaRepository, SnsJpaRepository snsJpaRepository, MemberAccessMapper memberAccessMapper) {
        this.memberJpaRepository = memberJpaRepository;
        this.emailJpaRepository = emailJpaRepository;
        this.careerJpaRepository = careerJpaRepository;
        this.snsJpaRepository = snsJpaRepository;
        this.memberAccessMapper = memberAccessMapper;
    }


    @Override
    public Member findMember(UUID memberIdx) throws BaseException{
        //멤버 엔티티와 연관관계 엔티티 가져오기
        MemberEntity member = memberJpaRepository.findById(memberIdx).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        //JPA엔티티를 연산 엔티티로 변환 후 리턴
        return memberAccessMapper.getMemberMapper(member, member.getEmails(), member.getCareers(), member.getSnses());
    }

    @Override
    public Boolean deleteMember(Member member) throws BaseException{
        //멤버 찾기 -> Service단에서 Transactional을 걸어놨기 때문에 select SQL 안나감
        MemberEntity memberEntity = memberJpaRepository.findById(member.getMemberIdx()).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        //JPA 엔티티에 update
        memberEntity.deleteMember(member.getStatus());

        //성공
        return true;
    }

    @Override
    public Boolean patchMemberImage(Member member) throws BaseException{
        MemberEntity memberEntity = memberJpaRepository.findById(member.getMemberIdx()).orElseThrow(() -> new BaseException(BaseErrorCode.MEMBER_NOT_EXIST));

        memberEntity.changeImageUrl(member.getProfileImgUrl());

        return true;
    }

    /**
     * 닉네임 중복 체크
     * */
    @Override
    public boolean checkNicknameExist(String nickname) {
        return memberJpaRepository.existsByNicknameIgnoreCase(nickname);
    }

    /**
     * 멤버 생성
     * */
    @Override
    public UUID persistMember(Member member) {
        MemberEntity memberEntity = memberAccessMapper.memberToEntity(member);

//        log.info(memberEntity.toString());
        return memberJpaRepository.save(memberEntity).getMemberIdx();
    }

    @Override
    public List<Member> findMemberList(List<UUID> memberIdxList) {
        List<MemberEntity> entities = new ArrayList<>();

        for (UUID uuid : memberIdxList) {
            entities.add(memberJpaRepository.findById(uuid).get());
        }

        return memberAccessMapper.entitiesToMembers(entities);
    }
}
