package edu.fzu.anop.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 用户信息更新资源类
 * @author SilverBay
 */
public class UserInfoUpdateResource {

    @NotNull
    @Length(min = 1, max = 31)
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
