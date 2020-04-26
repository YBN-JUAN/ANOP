package edu.fzu.anop.resource;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通知接收者添加资源
 *
 * @author Xue_Feng
 */
public class ReceiverAddResource implements Serializable {
    @NotNull
    private Integer notificationId;

    @NotNull
    private Integer groupId;

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
