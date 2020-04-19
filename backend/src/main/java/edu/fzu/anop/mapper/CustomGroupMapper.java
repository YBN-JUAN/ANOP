package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupResource;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomGroupMapper {
    GroupResource selectGroupByPrimary(Integer groupId);

    List<GroupResource> listUserCreateGroup(Integer userId);

    List<GroupResource> listUserManageGroup(Integer userId);
}
