package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class GroupUpdateResource implements Serializable {
    @Length(min = 1, max = 15)
    private String title;

    @Length(min = 1, max = 127)
    private String remark;

    @Range(min = 0, max = 2)
    private Byte permission;

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

    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }
}
