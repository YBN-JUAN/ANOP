package edu.fzu.anop.service;

import edu.fzu.anop.security.user.User;

public interface UserService {

    int resetPassword(User user, String newPassword);

}
