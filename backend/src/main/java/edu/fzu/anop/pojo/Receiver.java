package edu.fzu.anop.pojo;

import java.io.Serializable;

public class Receiver implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer notificationId;
    private Integer userId;
    private Byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Receiver{" +
            "id=" + id +
            ", notificationId=" + notificationId +
            ", userId=" + userId +
            ", status=" + status +
            '}';
    }
}