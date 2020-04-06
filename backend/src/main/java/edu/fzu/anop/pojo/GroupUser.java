package edu.fzu.anop.pojo;

import java.io.Serializable;

public class GroupUser implements Serializable {
    private Integer id;

    private Integer groupId;

    private Integer userId;

    private Byte role;

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

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "GroupUser{" +
            "id=" + id +
            ", groupId=" + groupId +
            ", userId=" + userId +
            ", role=" + role +
            '}';
    }
}