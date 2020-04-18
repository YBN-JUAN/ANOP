package edu.fzu.anop.mapper;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.resource.NotificationResource;

import java.util.List;

public interface CustomNotificationMapper {
    NotificationResource selectNotification(Integer notificationId, Integer groupId);

    List<NotificationResource> listNotification(Integer groupId);
}