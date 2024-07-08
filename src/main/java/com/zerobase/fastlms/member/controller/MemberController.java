package com.zerobase.fastlms.member.controller;

import com.zerobase.fastlms.member.model.MemberInput;
import com.zerobase.fastlms.member.repository.MemberRepository;
import com.zerobase.fastlms.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @RequestMapping("/member/login")
    public String login() {

        return "member/login";
    }

    // 회원가입
    @GetMapping("/member/register")
    public String register() {

        return "member/register";
    }

    @PostMapping("member/register")
    // client에게 데이터를 내리기 위해 model 인터페이스 사용
    public String registerSubmit(Model model, HttpServletRequest request, MemberInput parameter) {

        boolean result = memberService.register(parameter);

        model.addAttribute("result", result);

        return "member/register_complete";
    }

    // 주소의 구조
    // 프로토콜://도메인(IP):80(80은 web port http의 경우 기본적으로 80포트를 사용) = 도메인 서버가 주소와 IP를 매핑해줌/sub 기능/상세
    // 주소에 ?id=123가 붙으면 쿼리스트링(파라미터)를 의미

    // 기본적으로 function 하나당 주소 하나
    @GetMapping("/member/email-auth")
    public String emailAuth(Model model, HttpServletRequest request) {

        String uuid = request.getParameter("id");
        System.out.println(uuid);

        // 계정 활성화
        boolean result = memberService.emailAuth(uuid);
        model.addAttribute("result", result);

        return "member/email_auth";
    }

    @GetMapping("/member/info")
    public String memberInfo() {
        return "member/info";
    }

}
