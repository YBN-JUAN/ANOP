package edu.fzu.anop.service.impl;

import edu.fzu.anop.mapper.UserInfoMapper;
import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.pojo.example.UserInfoExample;
import edu.fzu.anop.resource.UserInfoUpdateResource;
import edu.fzu.anop.service.UserInfoService;
import edu.fzu.anop.util.PropertyMapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfo(int userInfoId) {
        return userInfoMapper.selectByPrimaryKey(userInfoId);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo, UserInfoUpdateResource resource) {
        UserInfoExample example = new UserInfoExample();
        UserInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(userInfo.getId());
        UserInfo newUserInfo = PropertyMapperUtil.map(resource, UserInfo.class);
        return userInfoMapper.updateByExample(newUserInfo, example);
    }

}
