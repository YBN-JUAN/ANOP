package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author SilverBay
 */
public class ValidEmailResource {

    @NotNull
    @Length(min = 1, max = 255)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
