package edu.fzu.anop.service.impl;

import edu.fzu.anop.mapper.CustomUserRequestMapper;
import edu.fzu.anop.mapper.GroupUserMapper;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.pojo.example.GroupUserExample;
import edu.fzu.anop.resource.GroupUserAddResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupUserServiceImpl implements GroupUserService {
    @Autowired
    GroupUserMapper groupUserMapper;
    @Autowired
    CustomUserRequestMapper customGroupUserMapper;
    @Autowired
    GroupService groupService;

    @Override
    public boolean hasAdminRole(int userId, int groupId) {
        GroupUserExample example = new GroupUserExample();
        GroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        criteria.andUserIdEqualTo(userId);
        List<GroupUser> groupUsers = groupUserMapper.selectByExample(example);
        if (groupUsers.size() > 0) {
            return groupUsers.get(0).getIsAdmin() == 1;
        }
        return false;
    }

    @Override
    public boolean isInGroup(int userId, int groupId) {
        GroupUserExample example = new GroupUserExample();
        GroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        criteria.andUserIdEqualTo(userId);
        List<GroupUser> groupUsers = groupUserMapper.selectByExample(example);
        return groupUsers.size() > 0;
    }

    @Override
    public int deleteGroupUser(GroupUser groupUser) {
        if (!groupService.hasAdminRole(SecurityUtil.getLoginUser(User.class).getId(), groupUser.getId())) {
            if (!hasAdminRole(SecurityUtil.getLoginUser(User.class).getId(), groupUser.getGroupId())) {
                if (!isInGroup(groupUser.getUserId(), groupUser.getGroupId())) {
                    return -1;
                }
            }
        }
        return groupUserMapper.deleteByPrimaryKey(groupUser.getId());
    }

    @Override
    public int updateGroupUserRole(GroupUser oldGroupUser, GroupUserUpdateResource resource) {
        if (!groupService.hasAdminRole(SecurityUtil.getLoginUser(User.class).getId(), oldGroupUser.getGroupId())) {
            if (!isInGroup(oldGroupUser.getUserId(), oldGroupUser.getGroupId())) {
                return -1;
            }
        }
        GroupUser newGroupUser = PropertyMapperUtil.map(resource, GroupUser.class);
        newGroupUser.setId(oldGroupUser.getId());
        return groupUserMapper.updateByPrimaryKeySelective(newGroupUser);
    }
}
