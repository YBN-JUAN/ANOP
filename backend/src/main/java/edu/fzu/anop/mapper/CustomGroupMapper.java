package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.Group;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomGroupMapper {
    List<Group> listUserManageGroups(Integer userId);
}
