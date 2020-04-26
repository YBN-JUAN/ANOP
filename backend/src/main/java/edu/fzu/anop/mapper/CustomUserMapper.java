package edu.fzu.anop.mapper;

import edu.fzu.anop.security.user.User;

public interface CustomUserMapper {

    User selectByUsername(String username);

    int insertSelective(User user);

    int updateByPrimaryKeySelective(User user);

}
