package edu.fzu.anop.service;

/**
 * @author SilverBay
 */
public interface UserService {

    /**
     * 判断旧密码是否正确
     *
     * @param oldPassword 修改密码请求的旧密码字段
     * @return 旧密码判断结果
     */
    boolean isRightOldPassword(String oldPassword);

    /**
     * 更改密码
     *
     * @param newPassword 修改密码请求的新密码字段
     * @return 是否更新成功
     */
    int resetPassword(String newPassword);

}
