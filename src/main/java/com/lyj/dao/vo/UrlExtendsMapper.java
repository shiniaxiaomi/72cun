package com.lyj.dao.vo;

import com.lyj.model.vo.UrlExtends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/7/1.
 */
public interface UrlExtendsMapper {

    List<UrlExtends> getRecommondData(Integer limit);

    List<UrlExtends> getAttentionData(Integer limit);

    List<UrlExtends> getHotData(Integer limit);

    List<UrlExtends> getSearchData(@Param("keyword")String keyword);


}
