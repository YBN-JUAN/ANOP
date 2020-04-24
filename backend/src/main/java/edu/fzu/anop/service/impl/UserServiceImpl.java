package edu.fzu.anop.service.impl;

import edu.fzu.anop.config.BeanConfig;
import edu.fzu.anop.mapper.CustomUserMapper;
import edu.fzu.anop.security.user.User;
import edu.fzu.anop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    CustomUserMapper customUserMapper;

    @Override
    public int resetPassword(User user, String newPassword) {
        PasswordEncoder passwordEncoder = new BeanConfig().passwordEncoder();
        user.setPassword(passwordEncoder.encode(newPassword));
        return customUserMapper.updateByPrimaryKeySelective(user);
    }
}
