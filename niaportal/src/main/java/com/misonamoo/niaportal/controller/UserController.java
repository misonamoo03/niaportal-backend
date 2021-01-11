package com.misonamoo.niaportal.controller;

import com.misonamoo.niaportal.service.UserService;
import com.misonamoo.niaportal.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // 로그인

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserVO login(@RequestBody Map<String, Object> params, HttpServletResponse response) throws Exception {
        UserVO vo = new UserVO();
        vo.setId(params.get("ruserId").toString());
        vo.setPw(params.get("ruserPw").toString());

        UserVO login = userService.login(vo);
        if (login == null) {
        } else {
            Cookie loginCookie = new Cookie("id", login.getId());
            loginCookie.setPath("/");
            loginCookie.setMaxAge(-1);
            response.addCookie(loginCookie);
        }
        return vo;
    }

    //로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)

    public String logout2(HttpServletResponse response, HttpServletRequest request) throws Exception {

        Cookie[] cookies = request.getCookies(); // 모든 쿠키의 정보를 cookies에 저장
        if (cookies != null) { // 쿠키가 한개라도 있으면 실행
            for (int i = 0; i < cookies.length; i++) {
                cookies[i].setMaxAge(0); // 유효시간을 0으로 설정
                response.addCookie(cookies[i]); // 응답 헤더에 추가
            }
        }
        return "redirect:/";
    }


    //아이디 찾기
    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    public String findId(@ModelAttribute UserVO vo) throws Exception {
        String result = userService.findId(vo);
        return result;
    }
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/mailSend")
    public String index() throws MessagingException, UnsupportedEncodingException {

        String to = ""; //받는 사람
        String from = ""; //보내는 사람
        String subject = "제목!!"; //제목
        String body = "내용@@"; //내용
//        StringBuilder body = new StringBuilder();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true, "UTF-8");
        mimeMessageHelper.setFrom(from, "진호");
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(body, true);
        javaMailSender.send(message);
        return "성공";
    }
    //비밀번호 재설정
    @RequestMapping(value = "/pwSet", method = RequestMethod.POST)
    public String setPw(@ModelAttribute UserVO vo) throws Exception {
        int result = userService.setPw(vo);
        String pass = "fail";
        if (result != 0) {
            pass = "success";
        }
        return pass;
    }

}
