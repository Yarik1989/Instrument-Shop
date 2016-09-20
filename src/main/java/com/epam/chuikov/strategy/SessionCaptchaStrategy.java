package com.epam.chuikov.strategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionCaptchaStrategy extends CaptchaStrategy {

    @Override
    public int read(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        return Integer.parseInt(String.valueOf(session.getAttribute("captchaId")));
    }

    @Override
    public void write(int id, HttpServletRequest request, HttpServletResponse response){
        super.write(id,request,response);
        HttpSession session = request.getSession();
        session.setAttribute("captchaId", id);
    }
}
