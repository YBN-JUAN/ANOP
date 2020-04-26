package edu.fzu.anop.resource;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知群组申请资源
 *
 * @author Xue_Feng
 */
public class UserRequestResource implements Serializable {
    private Integer id;

    private Integer groupId;

    private String groupName;

    private Integer userId;

    private String username;

    private String avatarUrl;

    private Byte isAccepted;

    private Date requestTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Byte getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Byte isAccepted) {
        this.isAccepted = isAccepted;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "GroupUserRequestResource{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", groupName='" + groupName + '\'' +
            ", userId=" + userId +
            ", username='" + username + '\'' +
            ", isAccepted=" + isAccepted +
            ", requestTime=" + requestTime +
            '}';
    }
}
