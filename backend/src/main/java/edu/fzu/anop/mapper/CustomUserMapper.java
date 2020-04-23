package edu.fzu.anop.mapper;

import edu.fzu.anop.security.user.User;

public interface CustomUserMapper {

    int insertSelective(User user);
}
