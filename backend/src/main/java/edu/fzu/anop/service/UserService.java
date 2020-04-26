package edu.fzu.anop.service;

/**
 * @author SilverBay
 */
public interface UserService {

    boolean isRightOldPassword(String oldPassword);

    int resetPassword(String newPassword);

}
