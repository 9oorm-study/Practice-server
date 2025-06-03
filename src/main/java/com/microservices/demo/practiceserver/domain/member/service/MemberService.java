package com.microservices.demo.practiceserver.domain.member.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface MemberService {

    void signUpMember(MemberRequestDTO.SignUpRequest request);

    Member postMemberInfo(MemberRequestDTO.PostMemberInfoRequest request);

    Slice<Member> getAllMembersInfo(Long cursor, Integer size);

    Member getMemberInfoById(Long memberId);

    Member updateMemberInfo(MemberRequestDTO.UpdateMemberInfoRequest request);

    void deleteMember(Long memberId);
}
