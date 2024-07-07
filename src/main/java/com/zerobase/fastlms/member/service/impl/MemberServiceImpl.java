package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.component.MailComponent;
import com.zerobase.fastlms.member.enity.Member;
import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MailComponent mailComponent;

    @Override
    public boolean register(MemberInput parameter) {

        Optional<Member> optionalMember = memberRepository.findById(parameter.getUserId());
        // isPresent 메서드를 사용해 데이터가 있는지 없는지 확인
        if (optionalMember.isPresent()) {
            // 현재 userId에 대한 데이터가 있음
            return false;
        }

        String uuid = UUID.randomUUID().toString();

        Member member = new Member();
        member.setUserId(parameter.getUserId());
        member.setUserName(parameter.getUserName());
        member.setPassword(parameter.getPassword());
        member.setPhone(parameter.getPhone());
        member.setRegDt(LocalDateTime.now());
        member.setEmailAuthYn(false);
        member.setEmailAuthKey(uuid);
        memberRepository.save(member);

        String email = parameter.getUserId();
        String subject = "fastlms 사이트 가입을 축하드립니다.";
        String text = "<p>fastlms 사이트 가입을 축하드립니다.</p><p>아래 링크를 클릭하셔서 가입을 완료하세요</p>"
                + "<div><a href='http://localhost:8080/member/email-auth?id=" + uuid + "'> 가입 완료 </a></div>";

        mailComponent.sendMail(email, subject, text);

        return true;
    }
}
