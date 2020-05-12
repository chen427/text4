package com.example.demoff.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class AuthorizationInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)throws Exception{
        if(request.getSession().getAttribute("loginUser")==null){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out=response.getWriter();
            out.print("<script>alert('请先进行登录,再进行后续操作!');location.href='"+
                    request.getContextPath()+"/login';<script>");
            return false;
        }else {
            return true;
        }
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView4){

    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler,Exception ex){

    }

}
