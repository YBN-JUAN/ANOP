package edu.fzu.anop.security.mapper;

import edu.fzu.anop.security.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} or email=#{username}")
    User loadUserByUsername(@Param("username") String username);
}
