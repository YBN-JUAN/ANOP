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

    boolean hasAdminRole(int userId, int groupId);

    boolean isPublicGroup(int groupId);

    boolean isPrivateGroup(int groupId);

    Group addGroup(GroupAddResource resource);

    Group getGroup(int groupId);

    PageInfo<List<Group>> getUserManageGroups(PageParmResource page);

    PageInfo<List<Group>> getUserCreateGroups(PageParmResource page);

    int deleteGroup(Group group);

    int updateGroup(Group oldGroup, GroupUpdateResource resource);
}
