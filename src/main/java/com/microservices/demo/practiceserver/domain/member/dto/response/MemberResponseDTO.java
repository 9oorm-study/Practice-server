package com.microservices.demo.practiceserver.domain.member.dto.response;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.time.LocalDate;
import java.util.List;

public class MemberResponseDTO {

    @Getter
    @Builder
    public static class CreateMemberInfoResponse {
        private Long memberId;
        private String username;
        private String email;
        private String nickname;

        public static CreateMemberInfoResponse toCreateMemberInfoResponse(Member member) {
            return MemberResponseDTO.CreateMemberInfoResponse.builder()
                    .memberId(member.getId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class MemberInfoResponse {
        private Long memberId;
        private String username;
        private String email;
        private String nickname;

        public static MemberInfoResponse toMemberInfoResponse(Member member) {
            return MemberResponseDTO.MemberInfoResponse.builder()
                    .memberId(member.getId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .build();
        }
        
    }

    @Getter
    @Builder
    public static class MemberInfoListResponse {
        private Long cursor;
        private Boolean hasNext;
        private List<MemberInfoResponse> members;

        public static MemberInfoListResponse toMemberInfoListResponse(Slice<Member> memberSlice) {
            List<MemberInfoResponse> members = memberSlice.getContent().stream()
                    .map(member -> MemberInfoResponse.builder()
                            .memberId(member.getId())
                            .username(member.getUsername())
                            .email(member.getEmail())
                            .nickname(member.getNickname())
                            .build())
                    .toList();

            Long nextCursor = memberSlice.hasNext() ? members.get(members.size() -1).getMemberId() : null;

            return MemberInfoListResponse.builder()
                    .cursor(nextCursor)
                    .hasNext(memberSlice.hasNext())
                    .members(members)
                    .build();
        }
    }

    @Getter
    @Builder
    public static class UpdateMemberInfoResponse {
        private String nickname;
        private Integer height;
        private Integer weight;
        private String introduce;
        private String phone;
        private LocalDate birth;

        public static UpdateMemberInfoResponse toUpdateMemberInfoResponse(Member member) {
            return MemberResponseDTO.UpdateMemberInfoResponse.builder()
                    .nickname(member.getNickname())
                    .height(member.getHeight())
                    .weight(member.getWeight())
                    .introduce(member.getIntroduce())
                    .phone(member.getPhone())
                    .birth(member.getBirth())
                    .build();
        }
    }

}
