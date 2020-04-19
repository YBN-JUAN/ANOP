package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.CustomNotificationMapper;
import edu.fzu.anop.mapper.NotificationMapper;
import edu.fzu.anop.mapper.ReceiverMapper;
import edu.fzu.anop.pojo.Notification;
import edu.fzu.anop.pojo.Receiver;
import edu.fzu.anop.pojo.example.ReceiverExample;
import edu.fzu.anop.resource.*;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupAuthService;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.service.TodoService;
import edu.fzu.anop.util.PageSortHelper;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    CustomNotificationMapper customNotificationMapper;
    @Autowired
    ReceiverMapper receiverMapper;
    @Autowired
    GroupUserService groupUserService;
    @Autowired
    GroupAuthService authService;
    @Autowired
    TodoService todoService;

    @Override
    public boolean isInGroup(int notificationId, int groupId) {
        return getNotification(notificationId, groupId) != null;
    }

    @Override
    public NotificationResource getNotificationInfo(int notificationId, int groupId) {
        if (!groupUserService.isInGroup(SecurityUtil.getLoginUser(User.class).getId(), groupId)) {
            return null;
        }
        return customNotificationMapper.selectNotification(notificationId, groupId);
    }

    @Override
    public Notification getNotification(int notificationId, int groupId) {
        Notification notification = notificationMapper.selectByPrimaryKey(notificationId);
        if (notification != null && notification.getGroupId() != groupId) {
            return null;
        }
        return notification;
    }

    @Override
    public PageInfo<List<NotificationResource>> listNotification(PageParmResource page, int groupId) {
        if (!groupUserService.isInGroup(SecurityUtil.getLoginUser(User.class).getId(), groupId)) {
            return null;
        }
        PageSortHelper.pageAndSort(page, NotificationResource.class);
        List<NotificationResource> notificationResources = customNotificationMapper.listNotification(groupId);
        return new PageInfo(notificationResources);
    }

    @Override
    public int deleteNotification(Notification notification) {
        if (!authService.canDeleteNotification(notification.getGroupId())) {
            return -1;
        }
        return notificationMapper.deleteByPrimaryKey(notification.getId());
    }

    @Override
    public int updateNotification(Notification oldNotification, NotificationUpdateResource resource) {
        if (!authService.canUpdateNotification(oldNotification.getGroupId())) {
            return -1;
        }
        ReceiverExample example = new ReceiverExample();
        ReceiverExample.Criteria criteria = example.createCriteria();
        criteria.andNotificationIdEqualTo(oldNotification.getId());
        Receiver receiver = new Receiver();
        receiver.setIsRead((byte) 1);
        receiverMapper.updateByExampleSelective(receiver, example);

        Notification newNotification = PropertyMapperUtil.map(resource, Notification.class);
        newNotification.setId(oldNotification.getId());
        newNotification.setCreationDate(new Date());
        return notificationMapper.updateByPrimaryKeySelective(newNotification);
    }

    @Override
    public int addNotification(NotificationAddResource resource, int groupId) {
        if (!authService.canAddNotification(groupId)) {
            return -1;
        }
        Notification notification = PropertyMapperUtil.map(resource, Notification.class);
        notification.setGroupId(groupId);
        notification.setCreationDate(new Date());
        notification.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        return notificationMapper.insert(notification);
    }

    @Override
    public int asTodo(Notification notification) {
        if (!authService.canTurnNotificationIntoTodo(notification.getGroupId())) {
            return -1;
        }
        TodoAddResource resource = new TodoAddResource();
        resource.setTitle(notification.getTitle());
        resource.setContent(notification.getContent());
        resource.setIsFavorite((byte) 0);
        resource.setIsImportant((byte) 0);
        return todoService.addTodo(resource) != null ? 1 : 0;
    }
}
