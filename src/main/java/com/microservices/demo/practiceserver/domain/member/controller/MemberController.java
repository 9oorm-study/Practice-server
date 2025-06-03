package com.microservices.demo.practiceserver.domain.member.controller;

import com.microservices.demo.practiceserver.domain.member.dto.request.MemberRequestDTO;
import com.microservices.demo.practiceserver.domain.member.dto.response.MemberResponseDTO;
import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public void signupMember(@RequestBody MemberRequestDTO.SignUpRequest request) {
        memberService.signUpMember(request);
    }

    @PostMapping("/members")
    public MemberResponseDTO.CreateMemberInfoResponse postMemberInfo(@RequestBody MemberRequestDTO.PostMemberInfoRequest request) {
        Member member = memberService.postMemberInfo(request);

        return MemberResponseDTO.CreateMemberInfoResponse.toCreateMemberInfoResponse(member);
    }


    @GetMapping("/members")
    public MemberResponseDTO.MemberInfoListResponse getAllMembersInfo(@RequestParam Long cursor,
                                                                      @RequestParam Integer size) {
        Slice<Member> memberSlice = memberService.getAllMembersInfo(cursor, size);

        return MemberResponseDTO.MemberInfoListResponse.toMemberInfoListResponse(memberSlice);
    }


    @GetMapping("/members/{memberId}")
    public MemberResponseDTO.MemberInfoResponse getMemberInfoById(@PathVariable Long memberId) {
        Member member = memberService.getMemberInfoById(memberId);

        return MemberResponseDTO.MemberInfoResponse.toMemberInfoResponse(member);
    }

    @PatchMapping("/members")
    public MemberResponseDTO.UpdateMemberInfoResponse updateMemberInfoResponse(@RequestBody MemberRequestDTO.UpdateMemberInfoRequest request) {
        Member member = memberService.updateMemberInfo(request);

        return MemberResponseDTO.UpdateMemberInfoResponse.toUpdateMemberInfoResponse(member);
    }

    @DeleteMapping("/members/{memberId}")
    public void deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
    }
}
