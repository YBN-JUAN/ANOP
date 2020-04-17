package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

public interface GroupService {
    Group addGroup(GroupAddResource resource);

    Group getGroup(int groupId);

    PageInfo<List<Group>> getUserManageGroup(PageParmResource page);

    PageInfo<List<Group>> getUserCreateGroup(PageParmResource page);

    int deleteGroup(Group group);

    int updateGroup(Group oldGroup, GroupUpdateResource resource);
}
