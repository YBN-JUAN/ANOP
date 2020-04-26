package edu.fzu.anop.resource;

import java.io.Serializable;

/**
 * 通知群组用户添加资源
 *
 * @author Xue_Feng
 */
public class GroupUserAddResource implements Serializable {
    private Integer groupId;

    private Integer userId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GroupUser{" +
            "groupId=" + groupId +
            ", userId=" + userId +
            '}';
    }
}
