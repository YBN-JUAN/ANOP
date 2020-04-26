package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.GroupUserResource;

import java.util.List;

/**
 * 自定义通知群组成员Mapper
 *
 * @author Xue_Feng
 */
public interface CustomGroupUserMapper {
    /**
     * 获取指定通知群组成员列表
     *
     * @param groupId 通知群组id
     * @return 通知群组成员列表
     */
    List<GroupUserResource> listGroupUser(Integer groupId);

    /**
     * 选取指定通知群组指定成员
     *
     * @param userId  成员id
     * @param groupId 通知群组id
     * @return 群组成员
     */
    GroupUserResource selectGroupUser(Integer userId, Integer groupId);
}
