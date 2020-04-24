package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.Group;
import edu.fzu.anop.resource.GroupAddResource;
import edu.fzu.anop.resource.GroupResource;
import edu.fzu.anop.resource.GroupUpdateResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

/**
 * 通知群组业务逻辑
 *
 * @author Xue_Feng
 */
public interface GroupService {
    /**
     * 指示指定的通知群组是否存在
     *
     * @param groupId 通知群组群号
     * @return 如果通知群组存在返回<code>true</code>，否则返回<code><false</code>
     */
    boolean hasGroup(int groupId);

    /**
     * 指示指定的用户是否为通知群组的创建者
     *
     * @param userId  用户id
     * @param groupId 通知群组群号
     * @return 如果用户是通知群组的创建者为返回<code>true</code>，否则返回<code><false</code>
     */
    boolean isGroupCreator(int userId, int groupId);

    /**
     * 指示指定的通知群组是否为公开群
     *
     * @param groupId 通知群组群号
     * @return 如果通知群组是公开群返回为<code>true</code>，否则返回<code><false</code>
     */
    boolean isPublicGroup(int groupId);

    /**
     * 指示指定的通知群组是否为私有群
     *
     * @param groupId 通知群组群号
     * @return 如果通知群组是私有群返回为<code>true</code>，否则返回<code><false</code>
     */
    boolean isPrivateGroup(int groupId);

    /**
     * 创建一个通知群组
     *
     * @param resource 通知群组创建参数
     * @return 返回新的通知群组
     */
    Group addGroup(GroupAddResource resource);

    /**
     * 获取指定通知群组
     *
     * @param groupId 通知群组群号
     * @return 如果存在指定通知群组返回通知群组，找不到返回<code>null</code>
     */
    Group getGroup(int groupId);

    /**
     * 获取指定通知群组信息
     *
     * @param groupId 通知群组群号
     * @return 如果存在指定通知群组返回通知群组信息，找不到返回<code>null</code>
     */
    GroupResource getGroupInfo(int groupId);

    /**
     * 获取用户订阅的通知群组信息列表
     *
     * @param page 分页参数
     * @return 通知群组信息列表
     */
    PageInfo<List<GroupResource>> listUserSubscribeGroupInfo(PageParmResource page);

    /**
     * 获取用户管理的通知群组信息列表
     *
     * @param page 分页参数
     * @return 通知群组信息列表
     */
    PageInfo<List<GroupResource>> listUserManageGroupInfo(PageParmResource page);

    /**
     * 获取用户创建的通知群组信息列表
     *
     * @param page 分页参数
     * @return 通知群组信息列表
     */
    PageInfo<List<GroupResource>> listUserCreateGroupInfo(PageParmResource page);

    /**
     * 退出用户订阅的通知群组
     *
     * @param group 通知群组群号
     * @return 如果用户不是指定通知群组的成员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int quitGroup(Group group);

    /**
     * 解散用户创建的通知群组
     *
     * @param group 通知群组群号
     * @return 如果用户不是指定通知群组的创建者返回<code>-1</code>，否则返回数据库受影响行数
     */
    int deleteGroup(Group group);

    /**
     * 更新指定通知群组信息
     *
     * @param oldGroup 原通知群群组
     * @param resource 通知群组更新参数
     * @return 如果用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int updateGroup(Group oldGroup, GroupUpdateResource resource);
}
