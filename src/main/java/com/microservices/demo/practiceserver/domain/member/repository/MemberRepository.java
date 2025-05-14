package com.microservices.demo.practiceserver.domain.member.repository;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByIsDeletedAndDeletedAtBefore(Boolean isDeleted, LocalDateTime deletedAtBefore);
}
