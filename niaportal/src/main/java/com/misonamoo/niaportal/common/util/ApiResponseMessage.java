package com.misonamoo.niaportal.common.util;

import com.misonamoo.niaportal.common.domain.CustomResponseMessage;

/**
 * HttpStatus에 따른 사용자 설정
 * @author Yohan
 */
public class ApiResponseMessage {
    public CustomResponseMessage setResponseMessage(int code) {
        CustomResponseMessage crm = new CustomResponseMessage();

        if (code == 100) {
            crm.setCode(100);
            crm.setMessage("필수인 변수값이 존재하지 않습니다.");
        }
        if (code == 101) {
            crm.setCode(101);
            crm.setMessage("아이디가 없습니다.");
        }
        if (code == 103) {
            crm.setCode(103);
            crm.setMessage("비밀번호가 없습니다.");
        }
        if (code == 104) {
            crm.setCode(104);
            crm.setMessage("비밀번호가 불일치 합니다.");
        }
        if (code == 400) {
            crm.setCode(400);
            crm.setMessage("서버 요청에 실패하였습니다.");
        }
        if (code == 500) {
            crm.setCode(500);
            crm.setMessage("서버측에서 오류가 발생하였습니다.");
        }

        crm.setCode(200);
        crm.setMessage("요청 성공");

        return crm;
    }
}
