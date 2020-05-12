package com.example.demoff;

import com.example.demoff.entity.Userbean;
import com.example.demoff.service.UserBeanService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DemoffApplicationTests {
    @Resource
    private UserBeanService userBeanService;
    @Test
    void contextLoads() {
      /*  //1.设置IP地址和端口
        Jedis jedis=new Jedis("localhost",6379);
        //设置密码
        jedis.auth("123");
        System.out.println("redis服务连接成功");

        //保存数据
        jedis.set("K1","测试1");
        jedis.set("K2","测试2");
        jedis.set("K3","测试3");

        System.out.println(jedis.get("K1"));

        //释放资源
        jedis.close();*/

        Userbean user=userBeanService.getUser("lisi");
        System.out.println("对象输出>>"+user);
        //没有使用缓存,第二次仍然还是需要从数据库中查询
        Userbean user1=userBeanService.getUser("lisi");
        System.out.println("对象输出>>"+user1);
    }
    @Test
    public void testLoginAndLogout(){
        //通过shiro.ini配置文件创建Realm
        IniRealm iniRealm=new IniRealm("classpath:shiro.ini");
        //创建SecurityManager
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //将secutityManager设置当前的运行环境中
        //SecurityUtils:是shiro的一个工具类
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        //从SecutityUtils里边创建一个subjiect
        //通过SecurityUtils获取getSubject得到一个返回值
        Subject subject=SecurityUtils.getSubject();
        //在认证提交前准备token(令牌)
        UsernamePasswordToken token=new UsernamePasswordToken("admin","123456");
        //执行认证提交
        try{
            subject.login(token);
        }catch (UnknownAccountException uae){
            System.out.println("未知账户异常"+uae);
        }catch (IncorrectCredentialsException ice){
            System.out.println("密码错误异常"+ice);
        }catch (LockedAccountException lae){
            System.out.println("账户锁定异常"+lae);
        }catch (ExcessiveAttemptsException eae){
            System.out.println("过多尝试次数异常"+eae);
        }catch (AuthenticationException ae){
            System.out.println("其他异常"+ae);
        }
        //是否认证通过
        boolean isAuthenticated=subject.isAuthenticated();
        System.out.println("是否认证通过:"+isAuthenticated);
        //退出操作
        subject.logout();
        //是否认证通过
        isAuthenticated=subject.isAuthenticated();
        System.out.println("是否认证通过:"+isAuthenticated);

    }

}
