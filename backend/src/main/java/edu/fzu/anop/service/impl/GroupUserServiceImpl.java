package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.CustomGroupUserMapper;
import edu.fzu.anop.mapper.GroupUserMapper;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.pojo.example.GroupUserExample;
import edu.fzu.anop.resource.GroupUserResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupAuthService;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.service.GroupUserService;
import edu.fzu.anop.util.PageSortHelper;
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
    CustomGroupUserMapper customGroupUserMapper;
    @Autowired
    GroupService groupService;
    @Autowired
    GroupAuthService authService;

    @Override
    public boolean hasAdminRole(int userId, int groupId) {
        GroupUser user = getGroupUser(userId, groupId);
        if (user != null && user.getIsAdmin() == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean hasCommonRole(int userId, int groupId) {
        GroupUser user = getGroupUser(userId, groupId);
        if (user != null && user.getIsAdmin() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isInGroup(int userId, int groupId) {
        GroupUser user = getGroupUser(userId, groupId);
        return user != null || groupService.isGroupCreator(userId, groupId);
    }

    @Override
    public GroupUserResource getGroupUserInfo(int userId, int groupId) {
        if (!isInGroup(SecurityUtil.getLoginUser(User.class).getId(), groupId)) {
            return null;
        }
        return customGroupUserMapper.selectGroupUser(userId, groupId);
    }

    @Override
    public GroupUser getGroupUser(int userId, int groupId) {
        GroupUserExample example = new GroupUserExample();
        GroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        criteria.andUserIdEqualTo(userId);
        List<GroupUser> groupUsers = groupUserMapper.selectByExample(example);
        if (groupUsers.size() > 0) {
            return groupUsers.get(0);
        }
        return null;
    }

    @Override
    public PageInfo<List<GroupUserResource>> listGroupUser(PageParmResource page, int groupId) {
        if (!isInGroup(SecurityUtil.getLoginUser(User.class).getId(), groupId)) {
            return null;
        }
        PageSortHelper.pageAndSort(page, GroupUserResource.class);
        List<GroupUserResource> groupUsers = customGroupUserMapper.listGroupUser(groupId);
        return new PageInfo(groupUsers);
    }


    @Override
    public int deleteGroupUser(GroupUser groupUser) {
        if (!authService.canDeleteGroupUser(groupUser.getGroupId())) {
            return -1;
        }
        return groupUserMapper.deleteByPrimaryKey(groupUser.getId());
    }

    @Override
    public int updateGroupUserRole(GroupUser oldGroupUser, GroupUserUpdateResource resource) {
        if (!authService.canUpdateGroupUserRole(oldGroupUser.getGroupId())) {
            return -1;
        }
        GroupUser newGroupUser = PropertyMapperUtil.map(resource, GroupUser.class);
        newGroupUser.setId(oldGroupUser.getId());
        return groupUserMapper.updateByPrimaryKeySelective(newGroupUser);
    }
}
