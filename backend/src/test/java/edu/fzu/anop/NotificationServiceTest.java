package edu.fzu.anop;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.service.NotificationService;
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
public class NotificationServiceTest {
    @BeforeEach
    void mockLoginUser() {
        MockUtil.mockLoginUser("user");
    }

    Logger logger = LoggerFactory.getLogger(NotificationServiceTest.class);

    @Autowired
    NotificationService notificationService;

    @Test
    @Transactional
    @Rollback
    void TestDenyOperation() {
        final int badResult = -1;

        Notification notification2 = notificationService.getNotification(8, 14);
        Assertions.assertNull(notification2, "用户获取不存在的通知");
        NotificationResource info = notificationService.getNotificationInfo(14, 14);
        Assertions.assertNull(info, "用户获取非自己加入的通知群组的通知");
        ReceiverNotificationResource notification = notificationService.getReceiverNotification(4, 8);
        Assertions.assertNull(notification, "用户作为管理员或者群主可以获取对于普通成员的通知");
        PageInfo<List<NotificationResource>> pageInfo = notificationService.listNotificationInfo(new PageParmResource(), 14);
        Assertions.assertNull(pageInfo, "用户获取非自己加入的通知群组的通知列表");
        PageInfo<List<ReceiverNotificationResource>> pageInfo1 = notificationService.listReceiverNotification(new PageParmResource(), 1);
        Assertions.assertNull(pageInfo1, "用户作为管理员或者群组可以获取对于普通成员的通知列表");
        GroupUnreadNotificationCountResource resource = notificationService.countGroupUnreadNotification(8);
        Assertions.assertNull(resource, "用户作为管理员或者群主可以获取对于普通成员的通知群组未读通知数");

        Notification notification1 = new Notification();
        notification1.setGroupId(9);
        notification1.setId(18);
        int result = notificationService.deleteNotification(notification1);
        Assertions.assertEquals(badResult, result, "用户不是管理员或者群主却可以删除通知");
        result = notificationService.addNotification(new NotificationAddResource(), 9);
        Assertions.assertEquals(badResult, result, "用户不是管理员或者群主却可以发布通知");
        result = notificationService.updateNotification(notification1, new NotificationUpdateResource());
        Assertions.assertEquals(badResult, result, "用户不是管理员或者群主却可以更新通知");
        notification1.setGroupId(8);
        result = notificationService.asTodo(notification1);
        Assertions.assertEquals(badResult, result, "用户不是普通成员却可以将通知转化为待办事项");
    }

    @Test
    @Transactional
    @Rollback
    void TestAcceptOperation() {
        NotificationResource info = notificationService.getNotificationInfo(8, 8);
        Assertions.assertNotNull(info, "用户无法获取自己加入的通知群组的通知");
        ReceiverNotificationResource notification = notificationService.getReceiverNotification(18, 9);
        Assertions.assertNotNull(notification, "用户作为普通成员无法获取对于普通成员的通知");
        PageInfo<List<NotificationResource>> pageInfo = notificationService.listNotificationInfo(new PageParmResource(), 1);
        Assertions.assertNotNull(pageInfo, "用户无法获取自己加入的通知群组的通知列表");
        PageInfo<List<ReceiverNotificationResource>> pageInfo1 = notificationService.listReceiverNotification(new PageParmResource(), 9);
        Assertions.assertNotNull(pageInfo1, "用户作为普通成员无法获取对于普通成员的通知列表");
        GroupUnreadNotificationCountResource resource = notificationService.countGroupUnreadNotification(9);
        Assertions.assertNotNull(resource, "用户作为普通成员无法获取对于普通成员的通知群组未读通知数");

        Notification notification1 = new Notification();
        notification1.setGroupId(8);
        notification1.setId(9);
        NotificationAddResource resource2 = new NotificationAddResource();
        resource2.setContent("121");
        resource2.setTitle("231");
        int result = notificationService.addNotification(resource2, 1);
        Assertions.assertTrue(result > 0, "用户是管理员或者群主却无法发布通知");
        NotificationUpdateResource resource1 = new NotificationUpdateResource();
        resource1.setContent("123");
        result = notificationService.updateNotification(notification1, resource1);
        Assertions.assertTrue(result > 0, "用户是管理员或者群主却无法更新通知");
        result = notificationService.deleteNotification(notification1);
        Assertions.assertTrue(result > 0, "用户是管理员或者群主却无法删除通知");
        notification1 = notificationService.getNotification(17, 9);
        result = notificationService.asTodo(notification1);
        Assertions.assertTrue(result > 0, "用户不是普通成员却无法将通知转化为待办事项");
    }

}
