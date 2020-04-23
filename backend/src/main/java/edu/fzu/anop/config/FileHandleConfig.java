package edu.fzu.anop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileHandleConfig implements WebMvcConfigurer {

    private static final String avatarImgPath = "\\src\\main\\resources\\static\\avatarimg\\";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    public static String getUploadPath() {
        return System.getProperty("user.dir") + avatarImgPath;
    }
}
