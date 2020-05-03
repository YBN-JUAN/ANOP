package edu.fzu.anop;

import edu.fzu.anop.config.BeanConfig;
import edu.fzu.anop.mapper.CustomUserMapper;
import edu.fzu.anop.resource.PasswordUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.UserService;
import edu.fzu.anop.util.MockUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService service;
    @Autowired
    CustomUserMapper userMapper;

    @BeforeEach
    public void mockLoginUser() {
        MockUtil.mockLoginUser("zwn");
    }

    @Test
    @Transactional
    @Rollback
    public void resetPasswordTest() {
        PasswordEncoder passwordEncoder = new BeanConfig().passwordEncoder();
        PasswordUpdateResource resource = new PasswordUpdateResource();
        resource.setOldPassword("123456");
        resource.setNewPassword("654321");

        User user = SecurityUtil.getLoginUser(User.class);
        String oldPassword = user.getPassword();
        Assertions.assertEquals(passwordEncoder.encode(resource.getOldPassword()),
                                oldPassword, "旧密码匹配失败");

        service.resetPassword(resource.getNewPassword());
        user = userMapper.selectByUsername("zwn");
        Assertions.assertEquals(passwordEncoder.encode(resource.getNewPassword()),
                                user.getPassword(), "新密码匹配失败");
    }

}
