package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.ValidEmail;
import edu.fzu.anop.pojo.example.ValidEmailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ValidEmailMapper {
    long countByExample(ValidEmailExample example);

    int deleteByExample(ValidEmailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ValidEmail record);

    int insertSelective(ValidEmail record);

    List<ValidEmail> selectByExample(ValidEmailExample example);

    ValidEmail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ValidEmail record, @Param("example") ValidEmailExample example);

    int updateByExample(@Param("record") ValidEmail record, @Param("example") ValidEmailExample example);

    int updateByPrimaryKeySelective(ValidEmail record);

    int updateByPrimaryKey(ValidEmail record);
}