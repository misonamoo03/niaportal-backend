package com.misonamoo.niaportal.controller;

import com.misonamoo.niaportal.service.UserService;
import com.misonamoo.niaportal.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/User")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    // 로그인
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public UserVO login(@RequestBody Map<String, Object> params, HttpServletRequest req, HttpServletResponse response) throws Exception {
        UserVO vo = new UserVO();
        vo.setId(params.get("Id").toString());
        vo.setPw(params.get("Pw").toString());
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

    // 로그아웃
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/";
    }

    //아이디 찾기
    @RequestMapping(value = "/findId", method = RequestMethod.GET)
    public String findId(@ModelAttribute UserVO vo) throws Exception {
        String result = userService.findId(vo);
        return result;
    }


}
