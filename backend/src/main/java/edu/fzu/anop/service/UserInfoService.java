package edu.fzu.anop.service;

import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.resource.UserInfoUpdateResource;

public interface UserInfoService {

    UserInfo getUserInfo(int userInfoId);

    int updateUserInfo(UserInfo userInfo, UserInfoUpdateResource resource);

}
