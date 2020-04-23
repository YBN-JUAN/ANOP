package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.*;

import java.util.List;

public interface NotificationService {
    boolean isInGroup(int notificationId, int groupId);

    NotificationResource getNotificationInfo(int notificationId, int groupId);

    ReceiverNotificationResource getReceiverNotification(int notificationId, int groupId);

    Notification getNotification(int notificationId, int groupId);

    PageInfo<List<NotificationResource>> listNotificationInfo(PageParmResource page, int groupId);

    PageInfo<List<ReceiverNotificationResource>> listReceiverNotification(PageParmResource page, int groupId);

    GroupUnreadNotificationCountResource countGroupUnreadNotification(int groupId);

    int deleteNotification(Notification notification);

    int updateNotification(Notification oldNotification, NotificationUpdateResource resource);

    int addNotification(NotificationAddResource resource, int groupId);

    int asTodo(Notification notification);
}