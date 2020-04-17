package edu.fzu.anop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //允许请求路径
        registry.addMapping("/**")
            //允许跨域的域名
            .allowedOrigins("*")
            //允许的请求方法
            .allowedMethods("*")
            //允许的请求头
            .allowedHeaders("*")
            //带上cookie信息
            .allowCredentials(true);
    }
}
