package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.RemindEmailDetail;

import java.util.List;

/**
 * 提醒邮件Mapper
 *
 * @author ZYF
 */
public interface RemindMailMapper {
    /**
     * 获取提醒邮件信息列表
     *
     * @return 提醒邮件信息列表
     */
    List<RemindEmailDetail> selectRemindMailDetails();
}
