package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.resource.GroupUserResource;

import java.util.List;

public interface CustomGroupUserMapper {
    List<GroupUserResource> listGroupUser(Integer groupId);

    GroupUserResource selectGroupUser(Integer userId, Integer groupId);
}
