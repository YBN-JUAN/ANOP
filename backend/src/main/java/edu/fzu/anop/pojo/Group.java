package edu.fzu.anop.pojo;

import java.io.Serializable;
import java.util.Date;

public class Group implements Serializable {
    private Integer id;

    private Integer userId;

    private String title;

    private String remark;

    private Date creationDate;

    private Byte permission;

    private static final long serialVersionUID = 1L;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
            ", title='" + title + '\'' +
            ", remark='" + remark + '\'' +
            ", creationDate=" + creationDate +
            ", permission=" + permission +
            '}';
    }
}