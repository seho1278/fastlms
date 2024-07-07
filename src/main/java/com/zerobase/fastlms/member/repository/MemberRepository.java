package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.enity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    // 패턴에 맞게 메서드를 작성하면 JPA가 자동으로 implement를 만들어준다
    // null값을 허용하기 위해 Optional 사용
    Optional<Member> findByEmailAuthKey(String emailAuthKey);

}
