package edu.fzu.anop.service;

import edu.fzu.anop.resource.ReceiverAddResource;
import edu.fzu.anop.resource.ReceiverResource;

import java.util.List;

/**
 * 通知接收者业务逻辑
 *
 * @author Xue_Feng
 */
public interface ReceiverService {
    /**
     * 已读一个通知
     *
     * @param resource 通知参数
     * @return 如果指定通知群组不存在、当前登录用户不是通知群组普通成员返回<code>-1</code>，否则返回数据库受影响行数
     */
    int addReceiver(ReceiverAddResource resource);

    /**
     * 获取通知读取人员信息列表
     *
     * @param notificationId 通知id
     * @param groupId        通知群组群号
     * @return 通知读取人员信息列表，如果指定通知不在指定通知群组不存在、
     * 当前登录用户不是通知群创建者或者管理员返回<code>null</code>
     */
    List<ReceiverResource> listReceiver(int notificationId, int groupId);
}
