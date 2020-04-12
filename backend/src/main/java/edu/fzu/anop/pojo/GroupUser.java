package edu.fzu.anop.pojo;

import java.io.Serializable;

public class GroupUser implements Serializable {
    private Integer id;

    private Integer groupId;

    private Integer userId;

    private Byte isAdmin;

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

    public Byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "GroupUser{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", userId=" + userId +
            ", isAdmin=" + isAdmin +
            '}';
    }
}