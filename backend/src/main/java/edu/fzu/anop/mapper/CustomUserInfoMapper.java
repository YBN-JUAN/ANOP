package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.UserInfoResource;

public interface CustomUserInfoMapper {

    UserInfoResource selectByUserId(Integer userId);
}
