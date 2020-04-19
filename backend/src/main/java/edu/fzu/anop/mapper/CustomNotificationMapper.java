package edu.fzu.anop.mapper;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.resource.NotificationResource;
import edu.fzu.anop.resource.ReceiverNotificationResource;

import java.util.List;

public interface CustomNotificationMapper {
    NotificationResource selectNotification(Integer notificationId, Integer groupId);

    List<NotificationResource> listNotification(Integer groupId);

    ReceiverNotificationResource selectReceiverNotification(Integer notificationId, Integer userId, Integer groupId);

    List<ReceiverNotificationResource> listReceiverNotification(Integer userId, Integer groupId);
}