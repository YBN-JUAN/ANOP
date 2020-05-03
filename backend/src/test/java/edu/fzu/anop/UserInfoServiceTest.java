package edu.fzu.anop;

import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.resource.UserInfoUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.UserInfoService;
import edu.fzu.anop.util.MockUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class UserInfoServiceTest {

    @Autowired
    UserInfoService service;

    @BeforeEach
    public void mockLoginUser() {
        MockUtil.mockLoginUser("zwn");
    }

    @Test
    @Transactional
    @Rollback
    public void getInfoTest() {
        User user = SecurityUtil.getLoginUser(User.class);
        UserInfo userInfo = service.getUserInfoByUserId(user.getId());

        Assertions.assertNotNull(userInfo, "不存在的用户");
        Assertions.assertEquals("zhaoweinan", userInfo.getNickname(), "用户昵称不匹配");
        Assertions.assertEquals("", userInfo.getAvatarUrl(), "用户头像路径不匹配");
        Assertions.assertEquals(user.getId(), userInfo.getUserId(), "用户ID不匹配");

        userInfo = service.getUserInfoByUserId(8);
        Assertions.assertNull(userInfo, "8号用户不存在，却可以获取到");

    }

    @Test
    @Transactional
    @Rollback
    public void updateInfoTest() {
        UserInfoUpdateResource resource = new UserInfoUpdateResource();
        resource.setNickname("Honey");
        resource.setAvatarUrl("url");

        UserInfo userInfo = service.getUserInfoByUserId(7);
        service.updateUserInfo(userInfo, resource);
        userInfo = service.getUserInfoByUserId(7);

        Assertions.assertEquals("Honey", userInfo.getNickname(), "更新用户昵称失败");
        Assertions.assertEquals("url", userInfo.getAvatarUrl(), "更新用户头像url失败");
    }

}
