package com.example.demoff.config.shiro;

import com.example.demoff.entity.Userbean;
import com.example.demoff.service.UserBeanService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserBeanService userBeanService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("权限认证--->MyShiroRealm.doGetAuthorizationInfo()");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("身份认证:MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号
        UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String usrName=token.getUsername();
        String usrPassword=new String(token.getPassword());
        System.out.println("usrName:"+usrName);
        System.out.println("usrPassword:"+usrPassword);

        //通过usrname从数据库中查找User对象,如果找到,  没找到.
        //实际项目中,这里可以根据实际情况做缓存,如果不做,Shiro自己也是有时间间隔机制,
        //2分钟之内不会重复执行该方法
       Userbean user=userBeanService.getUser(usrName);
        System.out.println("----->>user="+user);
        if(user==null){
            throw new UnknownAccountException("账号不存在!");
        }else  if(!user.getUsrPassword().equals(usrPassword)){
            throw new IncorrectCredentialsException("密码不正确1");
        }
        //认证信息
        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                user,//用户
                user.getUsrPassword(),//密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
