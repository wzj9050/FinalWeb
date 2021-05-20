package cn.wmyskxz.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: WebappAdrapter
 * @description: 注册拦截器配置类
 * @date: 2020/6/16
 * @author: cakin
 */
@Configuration
public class WebappAdapter implements WebMvcConfigurer {
    /**
     * @className: WebappAdapter
     * @description: 将登录拦截器添加进来
     * addPathPatterns()添加拦截
     * excludePathPatterns（）排除拦截
     * @date: 2020/6/16
     * @author: cakin
     */
    @Override
    public void addInterceptors( InterceptorRegistry registry ) {
        // 实例化登录拦截器，用于注册
        LoginInterceptor loginInterceptor = new LoginInterceptor();

        /***
         * 所有路径都被拦截  addPathPatterns("/**")
         * 允许通过   excludePathPatterns("/login", "/register")等等
         */
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns(loginInterceptor.getUrls());
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
