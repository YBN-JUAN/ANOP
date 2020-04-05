package edu.fzu.anop.pojo;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String name;
    private String remark;
    private Date creationDate;
    private Byte permission;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Group{" +
            "id=" + id +
            ", userId=" + userId +
            ", name='" + name + '\'' +
            ", remark='" + remark + '\'' +
            ", creationDate=" + creationDate +
            ", permission=" + permission +
            '}';
    }
}