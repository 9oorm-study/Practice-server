package com.microservices.demo.practiceserver.domain.member.controller;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public MemberResponseDTO.createMemberInfoResponse postMemberInfo(@RequestBody MemberRequestDTO.postMemberInfoRequest request) {
        Member member = memberService.postMemberInfo(request);

        return MemberResponseDTO.createMemberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    @GetMapping("/members")
    public MemberResponseDTO.memberInfoListResponse getAllMembersInfo() {
        List<Member> memberList = memberService.getAllMembersInfo();

        return MemberResponseDTO.memberInfoListResponse.builder()
                .members(memberList.stream()
                        .map(member -> MemberResponseDTO.memberInfoResponse.builder()
                                .memberId(member.getId())
                                .username(member.getUsername())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .build())
                        .toList())
                .build();
    }


    @GetMapping("/members/{memberId}")
    public MemberResponseDTO.memberInfoResponse getMemberInfoById(@PathVariable Long memberId) {
        Member member = memberService.getMemberInfoById(memberId);

        return MemberResponseDTO.memberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

}
