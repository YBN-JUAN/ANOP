package edu.fzu.anop.mapper;

import edu.fzu.anop.security.user.User;

/**
 * 自定义用户Mapper
 * @author SilverBay
 */
public interface CustomUserMapper {

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return User
     */
    User selectByUsername(String username);

    /**
     * 选择性的插入User字段
     *
     * @param user
     * @return int 成功插入记录条数
     */
    int insertSelective(User user);

    /**
     * 选择性的更新User字段
     *
     * @param user
     * @return int 成功修改的记录条数
     */
    int updateByPrimaryKeySelective(User user);

}
