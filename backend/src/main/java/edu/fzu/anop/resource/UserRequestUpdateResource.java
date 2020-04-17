package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserRequestUpdateResource implements Serializable {
    @NotNull
    @Range(min = 1, max = 2)
    private Byte isAccepted;

    public Byte getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Byte isAccepted) {
        this.isAccepted = isAccepted;
    }
}
