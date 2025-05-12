package com.microservices.demo.practiceserver.domain.member.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;

import java.util.List;

public interface MemberService {

    Member postMemberInfo(MemberRequestDTO.postMemberInfoRequest request);

    List<Member> getAllMembersInfo();

    Member getMemberInfoById(Long memberId);

    Member updateMemberInfo(MemberRequestDTO.updateMemberInfoRequest request);

    void deleteMember(Long memberId);
}
