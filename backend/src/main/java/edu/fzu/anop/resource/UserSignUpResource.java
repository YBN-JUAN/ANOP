package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserSignUpResource {

    @NotNull
    @Length(min = 1, max = 31)
    private String username;

    @NotNull
    @Length(min = 1, max = 255)
    private String email;

    @NotNull
    @Length(min = 5, max = 5)
    private String code;

    @NotNull
    @Length(min = 1, max = 255)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}