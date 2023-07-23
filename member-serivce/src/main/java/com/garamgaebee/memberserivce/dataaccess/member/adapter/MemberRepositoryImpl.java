package com.garamgaebee.memberserivce.dataaccess.member.adapter;

import com.garamgaebee.memberserivce.dataaccess.member.entity.Member;
import com.garamgaebee.memberserivce.dataaccess.member.mapper.MemberAccessMapper;
import com.garamgaebee.memberserivce.dataaccess.member.repository.MemberJpaRepository;
import com.garamgaebee.memberserivce.domain.ports.out.MemberRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private MemberAccessMapper memberAccessMapper;

    public MemberRepositoryImpl(MemberJpaRepository memberJpaRepository, MemberAccessMapper memberAccessMapper) {
        this.memberJpaRepository = memberJpaRepository;
        this.memberAccessMapper = memberAccessMapper;
    }


    @Override
    public Optional<Member> findById(Long memberIdx) {
        return memberJpaRepository.findById(memberIdx);
    }
}
