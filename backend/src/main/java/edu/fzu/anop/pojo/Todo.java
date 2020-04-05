package edu.fzu.anop.pojo;

import java.io.Serializable;
import java.util.Date;

public class Todo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Date beginDate;
    private Date endDate;
    private Date remindDate;
    private Integer categoryId;
    private Byte status;
    private Byte isImportant;
    private Byte isFavorite;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getIsImportant() {
        return isImportant;
    }

    public void setIsImportant(Byte isImportant) {
        this.isImportant = isImportant;
    }

    public Byte getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Byte isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public String toString() {
        return "Todo{" +
            "id=" + id +
            ", userId=" + userId +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            ", beginDate=" + beginDate +
            ", endDate=" + endDate +
            ", remindDate=" + remindDate +
            ", categoryId=" + categoryId +
            ", status=" + status +
            ", isImportant=" + isImportant +
            ", isFavorite=" + isFavorite +
            '}';
    }
}