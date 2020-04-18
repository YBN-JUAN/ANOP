package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.resource.GroupUserAddResource;
import edu.fzu.anop.resource.GroupUserResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

public interface GroupUserService {
    boolean hasAdminRole(int userId, int groupId);

    boolean isInGroup(int userId, int groupId);

    GroupUserResource getGroupUserInfo(int userId, int groupId);

    GroupUser getGroupUser(int userId, int groupId);

    PageInfo<List<GroupUserResource>> listGroupUser(PageParmResource page, int groupId);

    int deleteGroupUser(GroupUser groupUser);

    int updateGroupUserRole(GroupUser oldGroupUser, GroupUserUpdateResource resource);

}
