package edu.fzu.anop;

import edu.fzu.anop.resource.ReceiverAddResource;
import edu.fzu.anop.resource.ReceiverResource;
import edu.fzu.anop.service.ReceiverService;
import edu.fzu.anop.util.MockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReceiverServiceTest {
    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("user");
    }

    Logger logger = LoggerFactory.getLogger(ReceiverServiceTest.class);

    @Autowired
    ReceiverService receiverService;

    @Test
    @Transactional
    @Rollback
    void TestDenyOperation() {
        final int badResult = -1;

        ReceiverAddResource resource = new ReceiverAddResource();
        resource.setNotificationId(4);
        resource.setGroupId(9);
        int result = receiverService.addReceiver(resource);
        Assertions.assertEquals(badResult, result, "用户已读不存在的通知");
        resource.setGroupId(8);
        result = receiverService.addReceiver(resource);
        Assertions.assertEquals(badResult, result, "用户不是普通用户却可以标记通知为已读");

        List<ReceiverResource> list = receiverService.listReceiver(4, 9);
        Assertions.assertNull(list, "用户获取不存在的通知的已读未读人员列表");
        list = receiverService.listReceiver(16, 9);
        Assertions.assertNull(list, "用户不是管理员或者群主却可以获取通知的已读未读人员列表");
    }

    @Test
    @Transactional
    @Rollback
    void TestAcceptOperation() {
        MockUtil.mockLoginUser("admin");
        ReceiverAddResource resource = new ReceiverAddResource();
        resource.setNotificationId(9);
        resource.setGroupId(8);
        int result = receiverService.addReceiver(resource);
        Assertions.assertTrue(result > 0, "用户作为普通成员却不可以标记通知已读");
        resource.setNotificationId(7);
        result = receiverService.addReceiver(resource);
        Assertions.assertEquals(0, result, "用户作为普通成员却不可以标记通知已读");
        MockUtil.mockLoginUser("256");
        resource.setNotificationId(6);
        result = receiverService.addReceiver(resource);
        Assertions.assertTrue(result > 0, "用户作为普通成员却不可以标记通知已读");

        MockUtil.mockLoginUser("user");
        List<ReceiverResource> list = receiverService.listReceiver(1, 1);
        Assertions.assertNotNull(list, "用户作为管理员或者群主获取通知的已读未读人员列表");
    }

}
