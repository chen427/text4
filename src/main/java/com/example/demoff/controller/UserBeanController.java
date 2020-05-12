package com.example.demoff.controller;

import com.example.demoff.entity.Userbean;
import com.example.demoff.service.UserBeanService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class UserBeanController {
    @Resource
    private UserBeanService userBeanService;
    @RequestMapping(value = "/dologin")
    public String login(String usrName, String usrPassword, HttpServletRequest request,
                        Map<String,Object> map, HttpSession session){
        Userbean user=null;
        try{
            AuthenticationToken token=new UsernamePasswordToken(usrName,usrPassword);
            SecurityUtils.getSubject().login(token);
            user= (Userbean) SecurityUtils.getSubject().getPrincipal();
            session.setAttribute("loginUser",user);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            return "login";
        }
        return "main";

    }
}
