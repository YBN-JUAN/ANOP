package edu.fzu.anop.pojo;

import java.io.Serializable;
import java.util.Date;

public class ValidEmail implements Serializable {
    private Integer id;

    private String email;

    private String code;

    private Date expire;

    private Byte isValid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Byte getIsValid() {
        return isValid;
    }

    public void setIsValid(Byte isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "ValidEmail{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", code='" + code + '\'' +
            ", expire=" + expire +
            ", isValid=" + isValid +
            '}';
    }
}