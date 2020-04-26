package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.ReceiverResource;

import java.util.List;

/**
 * 自定义通知接收者Mapper
 *
 * @author Xue_Feng
 */

public interface CustomReceiverMapper {
    /**
     * 获取指定通知群组的指定通知接收者成员列表
     *
     * @param notificationId 通知id
     * @param groupId        通知群组id
     * @return 接收者成员列表
     */
    List<ReceiverResource> listReceiver(Integer notificationId, Integer groupId);
}
