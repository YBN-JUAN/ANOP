package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.CustomGroupMapper;
import edu.fzu.anop.mapper.GroupMapper;
import edu.fzu.anop.mapper.GroupUserMapper;
import edu.fzu.anop.mapper.UserRequestMapper;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.pojo.example.GroupUserExample;
import edu.fzu.anop.pojo.example.UserRequestExample;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupAuthService;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.util.PageSortHelper;
import edu.fzu.anop.util.PropertyMapperUtil;
import edu.fzu.anop.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private CustomGroupMapper customGroupMapper;
    @Autowired
    private UserRequestMapper userRequestMapper;
    @Autowired
    GroupUserMapper groupUserMapper;
    @Autowired
    private GroupAuthService authService;

    @Override
    public boolean hasGroup(int groupId) {
        Group group = groupMapper.selectByPrimaryKey(groupId);
        return group != null;
    }

    @Override
    public boolean isGroupCreator(int userId, int groupId) {
        Group group = groupMapper.selectByPrimaryKey(groupId);
        return group.getUserId() == userId;
    }

    @Override
    public boolean isPublicGroup(int groupId) {
        Group group = groupMapper.selectByPrimaryKey(groupId);
        return group.getPermission() == 1;
    }

    @Override
    public boolean isPrivateGroup(int groupId) {
        Group group = groupMapper.selectByPrimaryKey(groupId);
        return group.getPermission() == 2;
    }

    @Override
    public Group addGroup(GroupAddResource resource) {
        Group newGroup = PropertyMapperUtil.map(resource, Group.class);
        newGroup.setCreationDate(new Date());
        newGroup.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        groupMapper.insert(newGroup);
        return newGroup;
    }

    @Override
    public Group getGroup(int groupId) {
        Group group = groupMapper.selectByPrimaryKey(groupId);
        return group;
    }

    @Override
    public GroupResource getGroupInfo(int groupId) {
        return customGroupMapper.selectGroupByPrimary(groupId);
    }

    @Override
    public PageInfo<List<GroupResource>> listUserSubscribeGroupInfo(PageParmResource page) {
        PageSortHelper.pageAndSort(page, GroupResource.class);
        List<GroupResource> groups = customGroupMapper.listUserGroup(SecurityUtil.getLoginUser(User.class).getId(), (byte) 0);
        return new PageInfo(groups);
    }

    @Override
    public PageInfo<List<GroupResource>> listUserCreateGroupInfo(PageParmResource page) {
        PageSortHelper.pageAndSort(page, GroupResource.class);
        List<GroupResource> groups = customGroupMapper.listUserCreateGroup(SecurityUtil.getLoginUser(User.class).getId());
        return new PageInfo(groups);
    }

    @Override
    public int quitGroup(Group group) {
        if (!authService.canQuitGroup(group.getId())) {
            return -1;
        }
        GroupUserExample example = new GroupUserExample();
        GroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(group.getId());
        criteria.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId());
        return groupUserMapper.deleteByExample(example);
    }

    @Override
    public PageInfo<List<GroupResource>> listUserManageGroupInfo(PageParmResource page) {
        PageSortHelper.pageAndSort(page, GroupResource.class);
        List<GroupResource> groups = customGroupMapper.listUserGroup(SecurityUtil.getLoginUser(User.class).getId(), (byte) 1);
        return new PageInfo(groups);
    }

    @Override
    public int deleteGroup(Group group) {
        if (!authService.canDeleteGroup(group.getId())) {
            return -1;
        }
        return groupMapper.deleteByPrimaryKey(group.getId());
    }

    @Override
    public int updateGroup(Group oldGroup, GroupUpdateResource resource) {
        if (!authService.canUpdateGroupInfo(oldGroup.getId())) {
            return -1;
        }
        if (resource.getPermission() != null && resource.getPermission() == 2 && oldGroup.getPermission() != 2) {
            UserRequestExample example = new UserRequestExample();
            UserRequestExample.Criteria criteria = example.createCriteria();
            criteria.andGroupIdEqualTo(oldGroup.getId());
            criteria.andIsAcceptedEqualTo((byte) 0);
            UserRequest userRequest = new UserRequest();
            userRequest.setIsAccepted((byte) 2);
            userRequestMapper.updateByExampleSelective(userRequest, example);
        }

        Group newGroup = PropertyMapperUtil.map(resource, Group.class);
        newGroup.setId(oldGroup.getId());
        return groupMapper.updateByPrimaryKeySelective(newGroup);
    }
}
