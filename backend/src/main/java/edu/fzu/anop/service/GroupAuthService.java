package edu.fzu.anop.service;

/**
 * 通知中心权限检查业务逻辑
 *
 * @author Xue_Feng
 */
public interface GroupAuthService {
    /**
     * 指示登录用户是否能更新指定通知群组
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能更新通知群组返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canUpdateGroupInfo(int groupId);

    /**
     * 指示登录用户是否能删除指定通知群组
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能能除通知群组返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canDeleteGroup(int groupId);

    /**
     * 指示登录用户是否能更新指定通知群组成员角色
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能更新指定通知群组成员角色返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canUpdateGroupUserRole(int groupId);

    /**
     * 指示登录用户是否能删除指定通知群组成员
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能删除指定通知群组成员返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canDeleteGroupUser(int groupId);

    /**
     * 指示登录用户是否能处理指定通知群组的加入申请
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能定通知群组的加入申请返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canHandleUserRequest(int groupId);

    /**
     * 指示登录用户是否能在指定通知群组添加通知
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能在通知群组添加通知返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canAddNotification(int groupId);

    /**
     * 指示登录用户是否能在指定通知群组更新通知
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能在指定通知群组更新通知返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canUpdateNotification(int groupId);

    /**
     * 指示登录用户是否能在指定通知群组删除通知
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能在指定通知群组删除通知返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canDeleteNotification(int groupId);

    /**
     * 指示登录用户是否能在指定通知群组已读通知
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能在指定通知群组已读通知返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canMarkNotification(int groupId);

    /**
     * 指示登录用户是否能获取指定通知群组通知的读取人员列表
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能获取指定通知群组通知的读取人员列表返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canListReceiver(int groupId);

    /**
     * 指示登录用户是否能在指定通知群组将通知转化为待办事项
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能在指定通知群组将通知转化为待办事项返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canTurnNotificationIntoTodo(int groupId);

    /**
     * 指示登录用户是否能在指定通知群组获取订阅者的通知信息
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能在指定通知群组获取订阅者的通知信息返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canGetReceiverNotification(int groupId);

    /**
     * 指示登录用户是否能退出指定通知群组
     *
     * @param groupId 通知群组群号
     * @return 如果登录用户能退出指定通知群组返回<code>true</code>，否则返回<code>false</code>
     */
    boolean canQuitGroup(int groupId);
}
