package edu.fzu.anop.resource;

import io.swagger.models.auth.In;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class UserInfoUpdateResource {

    @NotNull
    private Integer id;

    @NotNull
    @Length(min = 0, max = 31)
    private String nickName;

    @NotNull
    @Length(min = 0, max = 31)
    private String userName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
