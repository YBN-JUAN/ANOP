package edu.fzu.anop.resource;

import java.io.Serializable;

public class GroupUserUpdateResource implements Serializable {
    private Byte isAdmin;

    public Byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }
}
