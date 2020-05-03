package edu.fzu.anop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

@Configuration
public class BeanConfig {

    /**
     * 密码加密
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            //对密码进行加密
            @Override
            public String encode(CharSequence charSequence) {
                //System.out.println(charSequence.toString());
                return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
            }

            //对密码进行判断匹配
            @Override
            public boolean matches(CharSequence charSequence, String s) {
                String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                boolean res = s.equals(encode);
                return res;
            }
        };
    }
}
