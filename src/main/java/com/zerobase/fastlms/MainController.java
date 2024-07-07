package com.zerobase.fastlms;

// 주소(논리적 인터넷 주소) 물리적인파일을 매핑
/*
하나의 주소에 대해 누가 매핑해주는가
후보 : 클래스, 속성, 메소드
클래스는 비효율적 -> x
 */

import com.zerobase.fastlms.component.MailComponent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;

// 주소만 매핑하는 클래스를 controller라고함
// spring에서 지원하는 controller 어노테이션을 달아 주소들을 매핑

// Controller의 경우 template을 리턴 문자열 리턴시 에러 발생
@Controller
@RequiredArgsConstructor
public class MainController {

    private final MailComponent mailComponent;

    // 스프링 어노테이션을 통해 매핑
    // 요청에 대한 매핑 = RequestMapping("루트")
    @RequestMapping("/")
    public String index() {

        /*
        String email = "seho1278@naver.com";
        String subject = "안녕하세요 제로베이스 입니다.";
        String text = "<p>안녕하세요.</p><p>반갑습니다.</p>";

        mailComponent.sendMail(email, subject, text);

         */

        // thymeleaf의 경우 templates, .html이 default
        // classPath를 변경하거나 html을 jsp로 바꾸고 싶은 경우 yml에 prefix와 suffix의 값을 변경해주면 된다.

        return "Index";
    }

    // spring -> MVC (View -> 템플릿엔진 화면에 내용을 출력(html))
    // .NET -> MVC (View -> 출력)
    // python django -> MTV(Template -> 화면출력)
    // 백엔드 과정 -> V(화면에 보여지는 부분) -> 프론트엔드 과정
    // V -> HTML -> 웹페이지
    // V -> json -> API


    // request -> WEB -> SERVER
    // response -> SERVER -> WEB

    @RequestMapping("/hello")
    public void hello(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        //getWriter을 사용하기 위해선 IOException을 throw 하거나 try catch 문으로 작성해야한다.
        PrintWriter printWriter = response.getWriter();

        String msg = "<html>" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body>" +
                "<p>hello</p> <p>fastlms website!!!</p>" +
                // 한글 작성시 한글이 깨진 상태로 출력 -> meta 태그로 해결
                "<p> 안녕하세요 </p>" +
                "</body>" +
                "</html>";

        printWriter.write(msg);
        printWriter.close();
    }

}
