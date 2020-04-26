package edu.fzu.anop.service;

import edu.fzu.anop.pojo.ValidEmail;
import edu.fzu.anop.resource.UserSignUpResource;
import edu.fzu.anop.security.user.User;

import javax.mail.MessagingException;

/**
 * @author SilverBay
 */
public interface SignUpService {

    ValidEmail getValidEmail(String email);
    boolean isSignedUpEmail(String email);
    boolean isSignedUpUsername(String userName);
    void saveValidEmail(String email, String code);
    String sendValidEmail(String email) throws MessagingException;
    User signUp(UserSignUpResource resource);
}
