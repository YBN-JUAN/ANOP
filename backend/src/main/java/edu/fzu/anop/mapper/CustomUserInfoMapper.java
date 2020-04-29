package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.UserInfoResource;

/**
 * 自定义用户信息Mapper
 *
 * @author SilverBay
 */
public interface CustomUserInfoMapper {

    /**
     * 获取用户信息封装资源类
     *
     * @param userId 用户Id
     * @return UserInfoResource 用户信息封装资源类
     */
    UserInfoResource selectByUserId(Integer userId);
}
