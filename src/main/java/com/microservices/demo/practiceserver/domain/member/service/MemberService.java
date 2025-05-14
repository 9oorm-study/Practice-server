package com.microservices.demo.practiceserver.domain.member.service;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;

import java.util.List;

public interface MemberService {

    Member postMemberInfo(MemberRequestDTO.PostMemberInfoRequest request);

    List<Member> getAllMembersInfo(Integer currentPage, Integer size);

    Member getMemberInfoById(Long memberId);

    Member updateMemberInfo(MemberRequestDTO.UpdateMemberInfoRequest request);

    void deleteMember(Long memberId);
}
