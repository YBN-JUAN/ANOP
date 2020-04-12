package edu.fzu.anop.pojo;

import java.io.Serializable;

public class Receiver implements Serializable {
    private Integer id;

    private Integer notificationId;

    private Integer userId;

    private Byte isRead;

    private static final long serialVersionUID = 1L;

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

    public Byte getIsRead() {
        return isRead;
    }

    public void setIsRead(Byte isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "Receiver{" +
            "id=" + id +
            ", notificationId=" + notificationId +
            ", userId=" + userId +
            ", isRead=" + isRead +
            '}';
    }
}