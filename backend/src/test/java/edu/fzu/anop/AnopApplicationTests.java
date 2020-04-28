package edu.fzu.anop;

import edu.fzu.anop.security.user.User;
import edu.fzu.anop.util.MockUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnopApplicationTests {
    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("user");
    }

    @Test
    void contextLoads() {
        User loginUser = SecurityUtil.getLoginUser(User.class);
        System.out.println(loginUser.getId());
        System.out.println(loginUser.getUsername());
        System.out.println(loginUser.getEmail());
        System.out.println(loginUser.getPassword());
        System.out.println(loginUser.getStatus());
    }

}
