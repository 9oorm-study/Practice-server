package com.microservices.demo.practiceserver.domain.member.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member postMemberInfo(MemberRequestDTO.postMemberInfoRequest request) {
        Member member = Member.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();

        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMembersInfo(Integer currentPage, Integer size) {

        return memberRepository.findAll();
    }

    @Override
    public Member getMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 id의 회원을 찾을 수 없습니다"));
    }

    @Override
    @Transactional
    public Member updateMemberInfo(MemberRequestDTO.updateMemberInfoRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("해당 id의 회원을 찾을 수 없습니다"));

        member.updateInfo(
                request.getNickname(),
                request.getHeight(),
                request.getWeight(),
                request.getIntroduce(),
                request.getPhone(),
                request.getBirth()
        );

        return member;
    }


    @Override
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 id의 회원을 찾을 수 없습니다"));

        member.softDelete();
    }

}
