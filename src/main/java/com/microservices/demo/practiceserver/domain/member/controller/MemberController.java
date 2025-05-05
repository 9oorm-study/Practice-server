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

    @PostMapping("/member")
    public MemberResponseDTO.postMemberInfoResponse postMemberInfo(@RequestBody MemberRequestDTO.postMemberInfoRequest request) {
        Member member = memberService.postMemberInfo(request);

        return MemberResponseDTO.postMemberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .build();
    }

    @GetMapping("/member")
    public List<MemberResponseDTO.getMmeberInfoResponse> getAllMembersInfo() {

        return memberService.getAllMembersInfo().stream()
                .map(member -> MemberResponseDTO.getMmeberInfoResponse.builder()
                        .memberId(member.getId())
                        .username(member.getUsername())
                        .email(member.getEmail())
                        .nickname(member.getNickname())
                        .password(member.getPassword())
                        .build())
                .toList();
    }

    @GetMapping("/member/{memberId}")
    public MemberResponseDTO.getMmeberInfoResponse getMemberInfoById(@PathVariable Long memberId) {
        Member member = memberService.getMemberInfoById(memberId);

        return MemberResponseDTO.getMmeberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .password(member.getPassword())
                .build();
    }

}
