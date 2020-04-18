package edu.fzu.anop.service.impl;

import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupAuthService;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupAuthServiceImpl implements GroupAuthService {
    @Autowired
    GroupUserService groupUserService;
    @Autowired
    GroupService groupService;

    private boolean hasBaseRole(int groupId) {
        Integer currentUserId = SecurityUtil.getLoginUser(User.class).getId();
        return groupService.isGroupCreator(currentUserId, groupId) || groupUserService.hasAdminRole(currentUserId, groupId);
    }

    private boolean hasMasterRole(int groupId) {
        Integer currentUserId = SecurityUtil.getLoginUser(User.class).getId();
        return groupService.isGroupCreator(currentUserId, groupId);
    }

    @Override
    public boolean canUpdateGroupInfo(int groupId) {
        return hasBaseRole(groupId);
    }

    @Override
    public boolean canDeleteGroup(int groupId) {
        return hasMasterRole(groupId);
    }

    @Override
    public boolean canUpdateGroupUserRole(int groupId) {
        return hasMasterRole(groupId);
    }

    @Override
    public boolean canDeleteGroupUser(int groupId) {
        return hasBaseRole(groupId);
    }

    @Override
    public boolean canHandleUserRequest(int groupId) {
        return hasBaseRole(groupId);
    }

    @Override
    public boolean canAddNotification(int groupId) {
        return hasBaseRole(groupId);
    }

    @Override
    public boolean canUpdateNotification(int groupId) {
        return hasBaseRole(groupId);
    }

    @Override
    public boolean canDeleteNotification(int groupId) {
        return hasBaseRole(groupId);
    }
}
