package edu.fzu.anop.service;

import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.resource.UserInfoResource;
import edu.fzu.anop.resource.UserInfoUpdateResource;

public interface UserInfoService {

    UserInfo getUserInfoByUserId(int userId);

    UserInfoResource getUserInfoResource(int userId);

    int updateUserInfo(UserInfo userInfo, UserInfoUpdateResource resource);

}
