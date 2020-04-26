package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 通知群组添加资源
 *
 * @author Xue_Feng
 */
public class GroupAddResource implements Serializable {
    @NotNull
    @Length(min = 0, max = 15)
    private String title;

    @NotNull
    @Length(min = 0, max = 127)
    private String remark;


    @NotNull
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
