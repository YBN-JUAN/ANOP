package edu.fzu.anop;

import edu.fzu.anop.config.BeanConfig;
import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.pojo.ValidEmail;
import edu.fzu.anop.resource.UserSignUpResource;
import edu.fzu.anop.resource.ValidEmailResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.SignUpService;
import edu.fzu.anop.service.UserInfoService;
import edu.fzu.anop.service.UserService;
import edu.fzu.anop.util.MockUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@SpringBootTest
public class SignUpServiceTest {

    @Autowired
    SignUpService signUpService;
    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userInfoService;

    @BeforeEach
    public void mockLoginUser() {
        MockUtil.mockLoginUser("zwn");
    }

    @Test
    @Transactional
    @Rollback
    public void validEmailTest() {

        String requestEmail = "zhaoweinan@qq.com";
        ValidEmailResource resource = new ValidEmailResource();
        resource.setEmail(requestEmail);

        Assertions.assertTrue(signUpService.isSignedUpEmail(resource.getEmail()), "验证已注册的邮箱");
        Assertions.assertFalse(signUpService.isSignedUpEmail("newemail@qq.com"), "验证未被注册的邮箱");

        requestEmail = "这不是一个邮箱";
        try {
            signUpService.sendValidEmail(requestEmail);
            Assertions.fail("向不合法的邮箱发送邮件成功");
        } catch (Exception e) {
            // TODO
        }

        requestEmail = "notExistMail@qq.com";
        try {
           signUpService.sendValidEmail(requestEmail);
           Assertions.fail("向不存在的邮箱发送邮件成功");
        } catch (Exception e) {
            // TODO
        }

        requestEmail = "saveEmail@163.com";
        String code = "123AB";
        try {
            signUpService.saveValidEmail(requestEmail, code);
        } catch (Exception e) {
            Assertions.fail("存储验证邮箱失败");
        }

        ValidEmail validEmail = signUpService.getValidEmail(requestEmail);
        Assertions.assertEquals((byte) 0, validEmail.getIsValid(), "新验证的邮箱有效位不为0");
        Assertions.assertTrue(validEmail.getExpire().after(new Date()), "新验证邮箱验证码过期时间错误");
        Assertions.assertEquals(code, validEmail.getCode(), "新验证邮箱验证码存储错误");
        Assertions.assertEquals(requestEmail, validEmail.getEmail(), "新验证邮箱邮箱存储错误");

    }

    @Test
    @Transactional
    @Rollback
    public void signUpTest() {

        UserSignUpResource resource = new UserSignUpResource();
        resource.setEmail("test2@qq.com");
        resource.setUsername("admin");
        resource.setCode("12345");

        ValidEmail validEmail = signUpService.getValidEmail(resource.getEmail());
        Assertions.assertNotNull(validEmail, "获取到不存在的验证邮箱类");
        Assertions.assertEquals(validEmail.getCode(),resource.getCode(), "验证码不匹配");
        Assertions.assertEquals((byte) 0, validEmail.getIsValid(), "邮箱验证状态错误");
        Assertions.assertFalse(signUpService.isSignedUpEmail(resource.getEmail()), "未注册邮箱获取状态错误");
        Assertions.assertTrue(signUpService.isSignedUpUsername(resource.getUsername()), "成功注册已存在的用户名");
        Assertions.assertTrue(validEmail.getExpire().before(new Date()), "验证码已过期，过期判断错误");

        resource.setEmail("test1@qq.com");
        resource.setUsername("newUser");
        resource.setCode("edcba");
        validEmail  = signUpService.getValidEmail(resource.getEmail());
        Assertions.assertFalse(signUpService.isSignedUpUsername(resource.getUsername()), "该用户名未被使用，但被拒绝注册");
        Assertions.assertNotEquals(validEmail.getCode(), resource.getCode(), "验证码不匹配，却被通过");
        Assertions.assertFalse(validEmail.getExpire().before(new Date()), "验证码未过期，过期判断错误");

        resource.setEmail("zhaoweinan@qq.com");
        resource.setUsername("zwn");
        validEmail = signUpService.getValidEmail(resource.getEmail());
        Assertions.assertEquals((byte) 1, validEmail.getIsValid(), "邮箱状态获取错误");
        
        resource.setUsername("newUser");
        resource.setEmail("newemail@qq.com");
        resource.setCode("QWERT");
        resource.setPassword("password");
        try {
            signUpService.saveValidEmail(resource.getEmail(), resource.getCode());
            signUpService.signUp(resource);
        } catch (Exception e) {
            Assertions.fail("用户注册信息均合法，却注册失败");
        }
        MockUtil.mockLoginUser("newUser");
        User user = SecurityUtil.getLoginUser(User.class);
        Assertions.assertEquals(resource.getUsername(), user.getUsername(), "新注册用户用户名存储错误");
        Assertions.assertEquals(resource.getEmail(), user.getEmail(), "新用户邮箱存储错误");
        PasswordEncoder passwordEncoder = new BeanConfig().passwordEncoder();
        Assertions.assertEquals(passwordEncoder.encode(resource.getPassword()), user.getPassword(), "新用户密码存储错误");
        Assertions.assertEquals((byte) 0, user.getStatus(), "新用户状态存储错误");

    }

}
