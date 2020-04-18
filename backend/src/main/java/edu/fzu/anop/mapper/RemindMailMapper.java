package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.RemindEmail;

import java.util.List;

public interface RemindMailMapper {
    List<RemindEmail> selectRemindMails();
}
