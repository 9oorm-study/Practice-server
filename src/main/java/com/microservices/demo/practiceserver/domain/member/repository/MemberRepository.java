package com.microservices.demo.practiceserver.domain.member.repository;

import com.microservices.demo.practiceserver.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
