package edu.fzu.anop.service.impl;

import edu.fzu.anop.mapper.CustomReceiverMapper;
import edu.fzu.anop.mapper.ReceiverMapper;
import edu.fzu.anop.pojo.Receiver;
import edu.fzu.anop.pojo.example.ReceiverExample;
import edu.fzu.anop.resource.ReceiverAddResource;
import edu.fzu.anop.resource.ReceiverResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupAuthService;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.service.NotificationService;
import edu.fzu.anop.service.ReceiverService;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通知接收者业务逻辑默认实现
 *
 * @author Xue_Feng
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class ReceiverServiceImpl implements ReceiverService {
    private static final byte READ = 1;
    private static final byte UNREAD = 0;
    @Autowired
    ReceiverMapper receiverMapper;
    @Autowired
    CustomReceiverMapper customReceiverMapper;
    @Autowired
    GroupAuthService authService;
    @Autowired
    GroupUserService groupUserService;
    @Autowired
    NotificationService notificationService;

    private Receiver selectReceiver(int notificationId, int userId) {
        ReceiverExample example = new ReceiverExample();
        ReceiverExample.Criteria criteria = example.createCriteria();
        criteria.andNotificationIdEqualTo(notificationId);
        criteria.andUserIdEqualTo(userId);
        List<Receiver> receivers = receiverMapper.selectByExample(example);
        if (receivers.size() > 0) {
            return receivers.get(0);
        }
        return null;
    }

    @Override
    public int addReceiver(ReceiverAddResource resource) {
        int currentUserId = SecurityUtil.getLoginUser(User.class).getId();
        if (!notificationService.isInGroup(resource.getNotificationId(), resource.getGroupId())) {
            return -1;
        }
        if (!authService.canMarkNotification(resource.getGroupId())) {
            return -1;
        }
        boolean isNew = false;
        Receiver receiver = selectReceiver(resource.getNotificationId(), currentUserId);
        if (receiver == null) {
            isNew = true;
            receiver = PropertyMapperUtil.map(resource, Receiver.class);
            receiver.setUserId(currentUserId);
        } else if (receiver.getIsRead() == READ) {
            return 0;
        }
        receiver.setIsRead(READ);
        if (isNew) {
            return receiverMapper.insert(receiver);
        }
        return receiverMapper.updateByPrimaryKey(receiver);
    }

    @Override
    public List<ReceiverResource> listReceiver(int notificationId, int groupId) {
        if (!notificationService.isInGroup(notificationId, groupId)) {
            return null;
        }
        if (!authService.canListReceiver(groupId)) {
            return null;
        }
        List<ReceiverResource> resources = customReceiverMapper.listReceiver(notificationId, groupId);
        for (ReceiverResource resource : resources) {
            if (resource.getIsRead() == null) {
                resource.setIsRead(UNREAD);
            }
        }
        return resources;
    }
}
