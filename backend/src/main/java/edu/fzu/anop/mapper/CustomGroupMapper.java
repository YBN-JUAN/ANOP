package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.GroupResource;

import java.util.List;

public interface CustomGroupMapper {
    GroupResource selectGroupByPrimary(Integer groupId);

    List<GroupResource> listUserCreateGroup(Integer userId);

    List<GroupResource> listUserGroup(Integer userId, Byte isAdmin);
}
