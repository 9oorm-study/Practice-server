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
    public MemberResponseDTO.CreateMemberInfoResponse postMemberInfo(@RequestBody MemberRequestDTO.PostMemberInfoRequest request) {
        Member member = memberService.postMemberInfo(request);

        return MemberResponseDTO.CreateMemberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    // 페이지네이션
    @GetMapping("/members")
    public MemberResponseDTO.MemberInfoListResponse getAllMembersInfo(@PathVariable Integer currentPage, Integer size) {
        List<Member> memberList = memberService.getAllMembersInfo(currentPage, size);

        return MemberResponseDTO.MemberInfoListResponse.builder()
                .members(memberList.stream()
                        .map(member -> MemberResponseDTO.MemberInfoResponse.builder()
                                .memberId(member.getId())
                                .username(member.getUsername())
                                .email(member.getEmail())
                                .nickname(member.getNickname())
                                .build())
                        .toList())
                .build();
    }


    @GetMapping("/members/{memberId}")
    public MemberResponseDTO.MemberInfoResponse getMemberInfoById(@PathVariable Long memberId) {
        Member member = memberService.getMemberInfoById(memberId);

        return MemberResponseDTO.MemberInfoResponse.builder()
                .memberId(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }

    @PatchMapping("/members")
    public MemberResponseDTO.UpdateMemberInfoResponse updateMemberInfoResponse(@RequestBody MemberRequestDTO.UpdateMemberInfoRequest request) {
        Member member = memberService.updateMemberInfo(request);

        return MemberResponseDTO.UpdateMemberInfoResponse.builder()
                .nickname(member.getNickname())
                .height(member.getHeight())
                .weight(member.getWeight())
                .introduce(member.getIntroduce())
                .phone(member.getPhone())
                .birth(member.getBirth())
                .build();
    }

    @DeleteMapping("/members/{memberId")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
