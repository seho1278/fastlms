package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.enity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    // null을 허용하기 위해 optional 형태로 데이터를 전달받는다.
//    Optional<Member> findByUserId(String UserId);
}
