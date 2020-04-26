package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.GroupResource;

import java.util.List;

/**
 * 自定义通知群组Mapper
 *
 * @author Xue_Feng
 */
public interface CustomGroupMapper {
    /**
     * 选取指定主键的通知
     *
     * @param groupId 主键
     * @return 通知
     */
    GroupResource selectGroupByPrimary(Integer groupId);

    /**
     * 获取指定用户创建的通知群组列表
     *
     * @param userId 用户id
     * @return 通知列表
     */
    List<GroupResource> listUserCreateGroup(Integer userId);

    /**
     * 获取指定用户管理的通知群组列表
     *
     * @param userId
     * @param isAdmin
     * @return
     */
    List<GroupResource> listUserGroup(Integer userId, Byte isAdmin);
}
