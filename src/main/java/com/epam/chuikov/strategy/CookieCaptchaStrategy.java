package com.epam.chuikov.strategy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieCaptchaStrategy extends CaptchaStrategy {

    @Override
    public int read(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies) {
            if (cookie.getName().equals("captchaId")) return Integer.parseInt(cookie.getValue());
        }
        return -1;
    }

    @Override
    public void write(int id, HttpServletRequest request, HttpServletResponse response){
        super.write(id,request,response);
        Cookie cookie = new Cookie("captchaId", String.valueOf(id));
        response.addCookie(cookie);
    }
}
