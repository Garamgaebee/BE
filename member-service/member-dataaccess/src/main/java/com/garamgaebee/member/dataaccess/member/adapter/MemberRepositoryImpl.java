package com.garamgaebee.member.dataaccess.member.adapter;


import com.garamgaebee.member.dataaccess.member.entity.CareerEntity;
import com.garamgaebee.member.dataaccess.member.entity.EmailEntity;
import com.garamgaebee.member.dataaccess.member.entity.MemberEntity;
import com.garamgaebee.member.dataaccess.member.entity.SnsEntity;
import com.garamgaebee.member.dataaccess.member.mapper.MemberAccessMapper;
import com.garamgaebee.member.dataaccess.member.repository.CareerJpaRepository;
import com.garamgaebee.member.dataaccess.member.repository.EmailJpaRepository;
import com.garamgaebee.member.dataaccess.member.repository.MemberJpaRepository;
import com.garamgaebee.member.dataaccess.member.repository.SnsJpaRepository;
import com.garamgeabee.member.domain.dto.GetMemberResponse;
import com.garamgeabee.member.domain.ports.out.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
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
    public Optional<GetMemberResponse> findMember(UUID memberIdx) {
        Optional<MemberEntity> member = memberJpaRepository.findById(memberIdx);
        Optional<List<EmailEntity>> email = emailJpaRepository.findEmailEntitiesByMemberIdx(memberIdx);
        Optional<List<CareerEntity>> career = careerJpaRepository.findCareerEntitiesByMemberIdx(memberIdx);
        Optional<List<SnsEntity>> sns = snsJpaRepository.findSnsEntitiesByMemberIdx(memberIdx);

        return Optional.ofNullable(memberAccessMapper.getMemberMapper(member.get(), email.get(), career.get(), sns.get()));
    }

    @Override
    public Boolean deleteMember(UUID memberIdx) {
        Optional<MemberEntity> member = memberJpaRepository.findById(memberIdx);
        if(!member.isPresent()) return false;

        member.get().deleteMember();

        return true;
    }

    @Override
    public Boolean patchMemberImage(String memberIdx, String imgUrl) {
        Optional<MemberEntity> member = memberJpaRepository.findById(UUID.fromString(memberIdx));
        if(!member.isPresent()) return false;

        member.get().changeImageUrl(imgUrl);

        return true;
    }

    @Override
    public UUID createMember(UUID memberIdx) {
        MemberEntity member = MemberEntity.builder().memberIdx(memberIdx).build();

        memberJpaRepository.save(member);

        return member.getMemberIdx();
    }
}
