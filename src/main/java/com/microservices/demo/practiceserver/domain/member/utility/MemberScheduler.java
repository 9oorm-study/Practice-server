package com.microservices.demo.practiceserver.domain.member.utility;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import com.microservices.demo.practiceserver.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberScheduler {

    private final MemberRepository memberRepository;

    @Transactional
    @Scheduled(cron = "0 30 4 * * *")
    public void memberHardDelete() {
        List<Member> softDeletedMembers = memberRepository
                .findByIsDeletedAndDeletedAtBefore(true, LocalDateTime.now().minusDays(7)); // deletedAt을 기준으로 7일이 지나면 삭제

        memberRepository.deleteAll(softDeletedMembers);
    }
}
