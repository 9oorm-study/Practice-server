package com.microservices.demo.practiceserver.domain.member.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member postMemberInfo(MemberRequestDTO.PostMemberInfoRequest request) {
        Member member = Member.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .nickname(request.getNickname())
                .build();

        return memberRepository.save(member);
    }

    @Override
    public Slice<Member> getAllMembersInfo(Long cursor, Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("id").ascending());

        if (cursor.equals(0L)) {
            return memberRepository.findAllByOrderByIdAsc(pageable);
        } else {
            return memberRepository.findByIdGreaterThanOrderByIdAsc(cursor, pageable);
        }

    }

    @Override
    public Member getMemberInfoById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 id의 회원을 찾을 수 없습니다"));
    }

    @Override
    @Transactional
    public Member updateMemberInfo(MemberRequestDTO.UpdateMemberInfoRequest request) {
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
