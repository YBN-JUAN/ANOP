package edu.fzu.anop.service.impl;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.mapper.GroupMapper;
import edu.fzu.anop.mapper.GroupUserMapper;
import edu.fzu.anop.mapper.UserRequestMapper;
import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.pojo.example.GroupExample;
import edu.fzu.anop.pojo.example.GroupUserExample;
import edu.fzu.anop.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GroupUserServiceImpl implements GroupUserService {
    @Autowired
    GroupMapper groupMapper;
    @Autowired
    GroupUserMapper groupUserMapper;
    @Autowired
    UserRequestMapper userRequestMapper;

    @Override
    public boolean hasAdminRole(int userId, int groupId) {
        GroupUserExample example = new GroupUserExample();
        GroupUserExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIdEqualTo(groupId);
        criteria.andUserIdEqualTo(userId);
        criteria.andIsAdminEqualTo((byte) 1);
        return groupUserMapper.countByExample(example) > 0;
    }

    @Override
    public int addGroupUser(int groupId, int userId) {
        return 0;
    }

    @Override
    public int deleteGroupUser(int groupId, int userId) {
        return 0;
    }

    @Override
    public int updateGroupRole(int groupId, int userId, int role) {
        return 0;
    }

    @Override
    public PageInfo<List<UserRequest>> getUserRequest() {
        return null;
    }

    @Override
    public int acceptOrDenyUserRequest(UserRequest request) {
        return 0;
    }
}
