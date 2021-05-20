package cn.wmyskxz.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @className: LoginController
 * @description: 登录控制器
 * @date: 2020/6/16
 * @author: cakin
 */
@Controller
public class LoginController {
    /**
     * Function description：user login in
     *
     * @param userName user name
     * @param password password
     * @param session  session
     * @return String login in page
     * @author cakin
     * @date 2020/6/16
     */
    @RequestMapping("/doLogin")
    public String login( String userName, String password, HttpSession session ) {
        if (userName==null){
            return"login";
        }else if (userName.equals("admin") && password.equals("123456")) {
            session.setAttribute("username", userName);
            //login successfully then turn to index page instead back to login page
            return "index";
        }else if (!userName.equals("admin") || !password.equals("123456")){
            return "hello";
        }
        return "login";
    }


    @RequestMapping("/login")
    public String loginView() {
        return "login";
    }
    @RequestMapping("/login_out")
    public String loginOut() {
        return "login_out";
    }



}
