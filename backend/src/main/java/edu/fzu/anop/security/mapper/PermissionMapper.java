package edu.fzu.anop.security.mapper;


import edu.fzu.anop.security.user.RolePermisson;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


//@Mapper
public interface PermissionMapper {

    @Select("SELECT A.NAME AS roleName,C.url FROM role AS A JOIN role_permission B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permission_id=C.id")
    List<RolePermisson> getRolePermissions();

}
