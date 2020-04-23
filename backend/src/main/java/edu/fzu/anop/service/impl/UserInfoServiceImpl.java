package edu.fzu.anop.service.impl;

import edu.fzu.anop.mapper.CustomUserInfoMapper;
import edu.fzu.anop.mapper.UserInfoMapper;
import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.pojo.example.UserInfoExample;
import edu.fzu.anop.resource.UserInfoResource;
import edu.fzu.anop.resource.UserInfoUpdateResource;
import edu.fzu.anop.service.UserInfoService;
import edu.fzu.anop.util.PropertyMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    CustomUserInfoMapper customUserInfoMapper;

    @Override
    public UserInfo getUserInfoByUserId(int userId) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        List<UserInfo> list =  userInfoMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public UserInfoResource getUserInfoResource(int userId) {
        return customUserInfoMapper.selectByUserId(userId);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo, UserInfoUpdateResource resource) {
        UserInfo newUserInfo = PropertyMapperUtil.map(resource, UserInfo.class);
        newUserInfo.setId(userInfo.getId());
        return userInfoMapper.updateByPrimaryKeySelective(newUserInfo);
    }

}
