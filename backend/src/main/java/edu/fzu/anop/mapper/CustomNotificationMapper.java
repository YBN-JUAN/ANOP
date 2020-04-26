package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.NotificationResource;
import edu.fzu.anop.resource.ReceiverNotificationResource;

import java.util.List;

/**
 * 自定义通知Mapper
 *
 * @author Xue_Feng
 */
public interface CustomNotificationMapper {
    /**
     * 选取指定通知群组指定通知
     *
     * @param notificationId 通知id
     * @param groupId        通知群组id
     * @return 通知
     */
    NotificationResource selectNotification(Integer notificationId, Integer groupId);

    /**
     * 获取指定通知群组通知列表
     *
     * @param groupId 通知群组id
     * @return 通知列表
     */
    List<NotificationResource> listNotification(Integer groupId);

    /**
     * 选取指定通知群组的指定接受成员的指定通知
     *
     * @param notificationId 通知id
     * @param userId         成员id
     * @param groupId        通知群组id
     * @return 通知
     */
    ReceiverNotificationResource selectReceiverNotification(Integer notificationId, Integer userId, Integer groupId);

    /**
     * 获取指定通知群组的指定成员的通知列表
     *
     * @param userId  成员id
     * @param groupId 通知群组id
     * @return 通知列表
     */
    List<ReceiverNotificationResource> listReceiverNotification(Integer userId, Integer groupId);

    /**
     * 统计指定成员的指定通知群组的未读通知数量
     *
     * @param userId  成员id
     * @param groupId 通知群组id
     * @return 未读通知数量
     */
    Long countGroupUnreadNotification(Integer userId, Integer groupId);
}