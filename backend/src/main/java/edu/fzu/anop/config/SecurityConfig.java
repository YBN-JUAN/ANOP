package edu.fzu.anop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * 安全配置类
 *
 * @author Xue_Feng
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String SIGN_IN_URL = "/v1/signin";
    private final String SIGN_OUT_URL = "/v1/signout";
    private final String SIGN_UP_URL = "/v1/signup";
    private final String VALID_EMAIL_URL = "/v1/valid_email";
    private final String SUCCESS_FORWARD_URL = "/user";
    private final String FAILURE_FORWARD_URL = "/failed";

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("*"));
//        configuration.setAllowedMethods(Arrays.asList("*"));
//        configuration.setAllowCredentials(true);
//        configuration.setAllowedHeaders(Arrays.asList("*"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic()
            .and()
            .authorizeRequests()
            //.antMatchers("/resource").hasRole("admin")
            .antMatchers(HttpMethod.POST, SIGN_IN_URL, SIGN_OUT_URL).permitAll()
            .antMatchers(HttpMethod.POST, SIGN_UP_URL, VALID_EMAIL_URL).permitAll()
            .anyRequest().authenticated()
            .and()
            .cors()
            .and()
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .formLogin()
            .loginProcessingUrl(SIGN_IN_URL)
            .successForwardUrl(SUCCESS_FORWARD_URL)
            .failureForwardUrl(FAILURE_FORWARD_URL)
            .and()
            .logout()
            .logoutUrl(SIGN_OUT_URL);
    }
}
