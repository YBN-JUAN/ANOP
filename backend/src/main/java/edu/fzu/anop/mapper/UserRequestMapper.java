package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.UserRequest;
import edu.fzu.anop.pojo.example.UserRequestExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserRequestMapper {
    long countByExample(UserRequestExample example);

    int deleteByExample(UserRequestExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRequest record);

    int insertSelective(UserRequest record);

    List<UserRequest> selectByExample(UserRequestExample example);

    UserRequest selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRequest record, @Param("example") UserRequestExample example);

    int updateByExample(@Param("record") UserRequest record, @Param("example") UserRequestExample example);

    int updateByPrimaryKeySelective(UserRequest record);

    int updateByPrimaryKey(UserRequest record);
}