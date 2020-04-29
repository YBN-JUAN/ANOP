package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * 邮箱验证资源类
 * @author SilverBay
 */
public class ValidEmailResource {

    @NotNull
    @Email
    @Length(min = 1, max = 255)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
