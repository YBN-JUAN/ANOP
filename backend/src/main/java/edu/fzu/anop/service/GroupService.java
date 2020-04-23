package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

public interface GroupService {
    boolean hasGroup(int groupId);

    boolean isGroupCreator(int userId, int groupId);

    boolean isPublicGroup(int groupId);

    boolean isPrivateGroup(int groupId);

    Group addGroup(GroupAddResource resource);

    Group getGroup(int groupId);

    GroupResource getGroupInfo(int groupId);

    PageInfo<List<GroupResource>> listUserSubscribeGroupInfo(PageParmResource page);

    PageInfo<List<GroupResource>> listUserManageGroupInfo(PageParmResource page);

    PageInfo<List<GroupResource>> listUserCreateGroupInfo(PageParmResource page);

    int quitGroup(Group group);

    int deleteGroup(Group group);

    int updateGroup(Group oldGroup, GroupUpdateResource resource);
}
