package edu.fzu.anop.pojo;

import java.io.Serializable;

public class Category implements Serializable {
    private Integer id;

    private Integer userId;

    private String typeName;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", userId=" + userId +
            ", typeName='" + typeName + '\'' +
            '}';
    }
}