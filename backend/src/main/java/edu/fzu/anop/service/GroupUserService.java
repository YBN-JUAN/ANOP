package edu.fzu.anop.service;

import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.resource.GroupUserAddResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;

public interface GroupUserService {
    boolean hasAdminRole(int userId, int groupId);

    boolean isInGroup(int userId, int groupId);

    int deleteGroupUser(GroupUser groupUser);

    int updateGroupUserRole(GroupUser oldGroupUser, GroupUserUpdateResource resource);

}
