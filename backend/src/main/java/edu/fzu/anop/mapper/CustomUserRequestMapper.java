package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.UserRequestResource;

import java.util.List;

/**
 * 自定义通知Mapper
 *
 * @author Xue_Feng
 */
public interface CustomUserRequestMapper {
    /**
     * 获取指定用户创建的通知群组列表
     *
     * @param userId 用户id
     * @return 通知群组列表
     */
    List<UserRequestResource> listUserRequest(Integer userId);

    /**
     * 获取指定用户管理的通知群组列表
     *
     * @param userId 用户id
     * @return 通知群组列表
     */
    List<UserRequestResource> listManageUserRequest(Integer userId);
}