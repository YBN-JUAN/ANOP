package edu.fzu.anop.mapper;

import edu.fzu.anop.pojo.Receiver;
import edu.fzu.anop.pojo.example.ReceiverExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ReceiverMapper {
    long countByExample(ReceiverExample example);

    int deleteByExample(ReceiverExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Receiver record);

    int insertSelective(Receiver record);

    List<Receiver> selectByExample(ReceiverExample example);

    Receiver selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Receiver record, @Param("example") ReceiverExample example);

    int updateByExample(@Param("record") Receiver record, @Param("example") ReceiverExample example);

    int updateByPrimaryKeySelective(Receiver record);

    int updateByPrimaryKey(Receiver record);
}