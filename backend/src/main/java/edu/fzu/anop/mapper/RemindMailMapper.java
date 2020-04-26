package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.RemindEmailDetail;

import java.util.List;

public interface RemindMailMapper {
    List<RemindEmailDetail> selectRemindMailDetails();
}
