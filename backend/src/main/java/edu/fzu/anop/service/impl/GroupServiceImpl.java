package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.GroupMapper;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.pojo.example.GroupExample;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.GroupService;
import edu.fzu.anop.util.SecurityUtil;
import edu.fzu.anop.util.ShallowMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Group addGroup(@RequestBody @Valid GroupAddResource resource) {
        Group newGroup = ShallowMapperUtil.map(resource, Group.class);
        newGroup.setCreationDate(new Date());
        newGroup.setUserId(SecurityUtil.getLoginUser(User.class).getId());
        groupMapper.insert(newGroup);
        return newGroup;
    }

    @Override
    public Group getGroup(int groupId) {
        Group group = groupMapper.selectByPrimaryKey(groupId);
        if (group == null) {
            return null;
        }
        return group;
    }

    @Override
    public PageInfo<List<Group>> getUserGroups(PageParmResource page) {
        GroupExample groupExample = new GroupExample();
        GroupExample.Criteria criteria = groupExample.createCriteria();
        criteria.andUserIdEqualTo(SecurityUtil.getLoginUser(User.class).getId());
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<Group> groups = groupMapper.selectByExample(groupExample);
        return new PageInfo(groups);
    }

    @Override
    public int deleteGroup(Group group) {
        if (!group.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }
        return groupMapper.deleteByPrimaryKey(group.getId());
    }

    @Override
    public int updateGroup(Group oldGroup, GroupUpdateResource resource) {
        if (!oldGroup.getUserId().equals(SecurityUtil.getLoginUser(User.class).getId())) {
            return -1;
        }
        Group newgroup = ShallowMapperUtil.map(resource, Group.class);
        return groupMapper.updateByPrimaryKeySelective(newgroup);
    }
}
