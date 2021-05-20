package cn.wmyskxz.springboot.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: LoginInterceptor
 * @description: 登录拦截器
 * @date: 2020/6/16
 * @author: cakin
 */
public class LoginInterceptor implements HandlerInterceptor {


    /**
     * 用于存储排除拦截的url  （登录/login.jsp, /css,/js,/img）
     */
    private List<String> urls = new ArrayList<>();

    /**
     * 功能描述：进入控制器之前拦截
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理
     * @return boolean 是否放行
     * @author cakin
     * @date 2020/6/16
     */
    @Override
    public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("username")!=null) {
            // Have login in, go!
            return true;
        } else {
            System.out.println("You do not login. No authority!");
            // No login, intercept and return to login page
            response.sendRedirect("login");
            return false;
        }
    }


    /**
     * 功能描述：设置能通过的url
     *
     * @return List<String>
     * @author cakin
     * @date 2020/6/16
     */
    public List<String> getUrls() {
        urls.add("/login");    // login url请求
        urls.add("/doLogin");  // 登录请求
        urls.add("/login_out");
        urls.add("/swagger-ui.html");

        // 静态资源
        urls.add("/img/*");
        urls.add("/js/*");
        urls.add("/css/*");
        return urls;
    }
}
