package edu.fzu.anop.security.service;

import edu.fzu.anop.security.user.Role;
import edu.fzu.anop.security.mapper.RoleMapper;
import edu.fzu.anop.security.mapper.UserMapper;
import edu.fzu.anop.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //查数据库
        User user = userMapper.loadUserByUsername(userName);
        if (null != user) {
            List<Role> roles = roleMapper.getRolesByUserId(user.getId());
            user.setAuthorities(roles);
        } else {
            throw new UsernameNotFoundException(userName);
        }

        return user;
    }
}
