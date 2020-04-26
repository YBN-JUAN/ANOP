package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.MailTodoResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: ZYF
 * @Date: 2020-4-25 16:54
 */
public interface CustomTodoMapper {
    List<MailTodoResource> selectByUserId(@Param(value="userId") int userId);
}
