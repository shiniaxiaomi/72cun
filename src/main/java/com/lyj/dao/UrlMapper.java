package com.lyj.dao;

import com.lyj.model.Url;
import com.lyj.model.UrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrlMapper {
    int deleteByExample(UrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Url record);

    int insertSelective(Url record);

    List<Url> selectByExample(UrlExample example);

    Url selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Url record, @Param("example") UrlExample example);

    int updateByExample(@Param("record") Url record, @Param("example") UrlExample example);

    int updateByPrimaryKeySelective(Url record);

    int updateByPrimaryKey(Url record);
}