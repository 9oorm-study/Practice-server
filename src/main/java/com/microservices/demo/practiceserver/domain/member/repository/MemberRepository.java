package com.microservices.demo.practiceserver.domain.member.repository;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIsDeletedAndDeletedAtBefore(Boolean isDeleted, LocalDateTime deletedAtBefore);
    Optional<Member> findByUsername(String username);
}
