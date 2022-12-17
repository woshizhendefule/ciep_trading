package com.chengyu.ciep_trading.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局请求拦截器
 *
 * @author CL
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //设置允许跨域的路径
        registry.addMapping("/**")
                //当**Credentials为true时，**Origin不能为星号，需为具体的ip地址
                .allowedOriginPatterns("*")
                //是否允许证书
                .allowCredentials(true)
                //设置允许的方法和请求头
                .allowedMethods("POST", "GET")
                .allowedHeaders("*")
                //跨域允许时间
                .maxAge(3600);
    }
}
