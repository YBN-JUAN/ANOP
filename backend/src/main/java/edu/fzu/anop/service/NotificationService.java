package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.resource.*;

import java.util.List;

/**
 * 通知业务逻辑
 *
 * @author Xue_Feng
 */
public interface NotificationService {
    /**
     * 指示指定通知是否在指定通知群组中
     *
     * @param notificationId 通知id
     * @param groupId        通知群组群号
     * @return 如果指定通知是在指定通知群组中返回<code>true</code>，否则返回<code>false</code>
     */
    boolean isInGroup(int notificationId, int groupId);

    /**
     * 获取指定通知群组的指定通知信息
     *
     * @param notificationId 通知id
     * @param groupId        通知群组群号
     * @return 通知信息，如果用户不在指定的通知群组中返回<code>null</code>
     */
    NotificationResource getNotificationInfo(int notificationId, int groupId);

    /**
     * 获取订阅者的指定通知群组的指定通知信息
     *
     * @param notificationId 通知id
     * @param groupId        通知群组群号
     * @return 订阅者的通知信息，如果用户不是该群的普通成员返回<code>null</code>
     */
    ReceiverNotificationResource getReceiverNotification(int notificationId, int groupId);

    /**
     * 获取指定通知群组的指定通知
     *
     * @param notificationId 通知id
     * @param groupId        通知群组群号
     * @return 通知，如果指定通知不在指定通知群组中返回<code>null</code>
     */
    Notification getNotification(int notificationId, int groupId);

    /**
     * 获取指定通知群组的通知信息分页列表
     *
     * @param page    分页参数
     * @param groupId 通知群组群号
     * @return 通知信息分页列表，如果用户不在指定的通知群组中返回<code>null</code>
     */
    PageInfo<List<NotificationResource>> listNotificationInfo(PageParmResource page, int groupId);

    /**
     * 获取订阅者的指定通知群组的通知信息分页列表
     *
     * @param page    分页参数
     * @param groupId 通知群组群号
     * @return 订阅者的通知信息分页列表，如果用户不是该群的普通成员返回<code>null</code>
     */
    PageInfo<List<ReceiverNotificationResource>> listReceiverNotification(PageParmResource page, int groupId);

    /**
     * 获取指定通知群组未读通知数信息
     *
     * @param groupId 通知群组群号
     * @return 未读通知数信息，如果用户不是该群的普通成员返回<code>null</code>
     */
    GroupUnreadNotificationCountResource countGroupUnreadNotification(int groupId);

    /**
     * 删除指定通知
     *
     * @param notification 通知
     * @return 如果用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int deleteNotification(Notification notification);

    /**
     * 更新指定通知
     *
     * @param oldNotification 旧通知
     * @param resource        通知更新参数
     * @return 如果用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int updateNotification(Notification oldNotification, NotificationUpdateResource resource);

    /**
     * 添加新通知
     *
     * @param resource 通知添加参数
     * @param groupId  通知群组群号
     * @return 如果用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int addNotification(NotificationAddResource resource, int groupId);

    /**
     * 将转换通知转换为用户代办事项
     *
     * @param notification
     * @return 如果用户不在指定的通知群组中返回<code>-1</code>，否则返回数据库受影响行数
     */
    int asTodo(Notification notification);
}
