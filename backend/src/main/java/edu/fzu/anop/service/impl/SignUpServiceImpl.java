package edu.fzu.anop.service.impl;

import edu.fzu.anop.config.BeanConfig;
import edu.fzu.anop.mapper.CustomUserMapper;
import edu.fzu.anop.mapper.UserInfoMapper;
import edu.fzu.anop.mapper.ValidEmailMapper;
import edu.fzu.anop.pojo.UserInfo;
import edu.fzu.anop.pojo.ValidEmail;
import edu.fzu.anop.pojo.example.ValidEmailExample;
import edu.fzu.anop.resource.UserSignUpResource;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.MailService;
import edu.fzu.anop.service.SignUpService;
import edu.fzu.anop.util.PropertyMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    MailService mailService;
    @Autowired
    ValidEmailMapper validEmailMapper;
    @Autowired
    CustomUserMapper customUserMapper;
    @Autowired
    UserInfoMapper userInfoMapper;

    private static final int codeLength = 5;
    private static final char[] codeChars = {'A','B','C','D',
            'E','F','G','H','I','J','K','L','M', 'N','O', 'P',
            'Q','R','S','T','U','V','W','X','Y', 'Z', '0','1',
            '2','3','4','5','6','7','8','9'};
    private static final long expireMesc = 1000 * 60 * 10;

    private static final String emailSubject = "ANOP:用户注册验证码";
    private static final String emailContent = "您的注册验证码为:";

    @Override
    public ValidEmail getValidEmail(String email) {
        ValidEmailExample example = new ValidEmailExample();
        ValidEmailExample.Criteria criteria = example.createCriteria();
        criteria.andEmailEqualTo(email);
        List<ValidEmail> list = validEmailMapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public boolean isSignedUp(String email) {
        ValidEmail validEmail = getValidEmail(email);
        return (validEmail != null && validEmail.getIsValid() == 1);
    }

    @Override
    public void saveValidEmail(String email, String code) {
        ValidEmail validEmail = getValidEmail(email);
        if(validEmail == null) {
            validEmail = new ValidEmail();
            validEmail.setEmail(email);
            validEmail.setCode(code);
            Date expire = new Date();
            expire.setTime(expire.getTime() + expireMesc);
            validEmail.setExpire(expire);
            validEmailMapper.insertSelective(validEmail);
        }
        else {
            validEmail.setCode(code);
            Date expire = new Date();
            expire.setTime(expire.getTime() + expireMesc);
            validEmail.setExpire(expire);
            validEmailMapper.updateByPrimaryKey(validEmail);
        }
    }

    @Override
    public String sendValidEmail(String email) throws MessagingException {
        String code = "";
        Random random = new Random();
        for(int i=0; i<codeLength; i++) {
            code += codeChars[random.nextInt(codeChars.length)];
        }
        mailService.sendHtmlMail(email, emailSubject,emailContent + code);

        saveValidEmail(email, code);
        return code;
    }

    @Override
    public User signUp(UserSignUpResource resource) {
        User user = addUser(resource);
        addUserInfo(user.getId());
        setEmailValid(resource);
        return user;
    }

    private User addUser(UserSignUpResource resource) {
        User user = PropertyMapperUtil.map(resource, User.class);
        PasswordEncoder passwordEncoder = new BeanConfig().passwordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        customUserMapper.insertSelective(user);
        return user;
    }

    private ValidEmail setEmailValid(UserSignUpResource resource) {
        ValidEmail validEmail = getValidEmail(resource.getEmail());
        validEmail.setIsValid((byte) 1);
        validEmailMapper.updateByPrimaryKey(validEmail);
        return  validEmail;
    }

    private UserInfo addUserInfo(int userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setCreationTime(new Date());
        userInfo.setNickname("User" + userId);
        userInfo.setAvatarUrl("");
        userInfoMapper.insertSelective(userInfo);
        return userInfo;
    }
}
