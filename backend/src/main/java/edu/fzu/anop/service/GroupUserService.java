package edu.fzu.anop.service;

import com.github.pagehelper.PageInfo;
import edu.fzu.anop.pojo.GroupUser;
import edu.fzu.anop.resource.GroupUserResource;
import edu.fzu.anop.resource.GroupUserUpdateResource;
import edu.fzu.anop.resource.PageParmResource;

import java.util.List;

/**
 * 通知群组成员业务逻辑
 *
 * @author Xue_Feng
 */
public interface GroupUserService {
    /**
     * 指示指定用户是否在指定通知群组有管理员权限
     *
     * @param userId  用户id
     * @param groupId 通知群组群号
     * @return 如果指定用户在指定通知群组有管理员权限返回<code>true</code>，否则返回<code>false</code>
     */
    boolean hasAdminRole(int userId, int groupId);

    /**
     * 指示指定用户是否在指定通知群组有普通成员权限
     *
     * @param userId  用户id
     * @param groupId 通知群组群号
     * @return 如果指定用户在指定通知群组有普通成员权限返回<code>true</code>，否则返回<code>false</code>
     */
    boolean hasCommonRole(int userId, int groupId);

    /**
     * 指示指定用户是否在指定通知群组中
     *
     * @param userId  用户id
     * @param groupId 通知群组群号
     * @return 如果指定用户在指定通知群组中返回<code>true</code>，否则返回<code>false</code>
     */
    boolean isInGroup(int userId, int groupId);

    /**
     * 获取在指定通知群组中指定成员信息
     *
     * @param userId  成员id
     * @param groupId 通知群组群号
     * @return 成员信息，如果当前登录的用户不在指定通知群组中返回<code>null</code>
     */
    GroupUserResource getGroupUserInfo(int userId, int groupId);

    /**
     * 获取在指定通知群组中指定成员
     *
     * @param userId  成员id
     * @param groupId 通知群组群号
     * @return 成员信息，如果指定成员不在指定通知群组中返回<code>null</code>
     */
    GroupUser getGroupUser(int userId, int groupId);

    /**
     * 获取在指定通知群组中指定成员信息列表
     *
     * @param page    分页参数
     * @param groupId 通知群组群号
     * @return 成员信息列表，如果当前登录用户不在指定通知群组中返回<code>null</code>
     */
    PageInfo<List<GroupUserResource>> listGroupUser(PageParmResource page, int groupId);

    /**
     * 删除在指定通知群组中指定成员
     *
     * @param groupUser
     * @return 成员信息列表，如果当前登录用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int deleteGroupUser(GroupUser groupUser);

    /**
     * 更新在指定通知群组中指定成员权限
     *
     * @param oldGroupUser 旧成员
     * @param resource     更新成员参数
     * @return 如果前登录用户不是指定通知群组的创建者或者管理员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int updateGroupUserRole(GroupUser oldGroupUser, GroupUserUpdateResource resource);

}
