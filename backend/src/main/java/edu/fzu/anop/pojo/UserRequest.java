package edu.fzu.anop.pojo;

import java.io.Serializable;
import java.util.Date;

public class UserRequest implements Serializable {
    private Integer id;

    private Integer groupId;

    private Integer userId;

    private Byte isAccepted;

    private Date requestTime;

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return "UserRequest{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", userId=" + userId +
            ", isAccepted=" + isAccepted +
            ", requestTime=" + requestTime +
            '}';
    }
}