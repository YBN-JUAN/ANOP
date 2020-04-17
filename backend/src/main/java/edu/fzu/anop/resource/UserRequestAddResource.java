package edu.fzu.anop.resource;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserRequestAddResource implements Serializable {
    @NotNull
    private Integer groupId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
