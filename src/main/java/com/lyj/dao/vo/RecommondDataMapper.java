package com.lyj.dao.vo;

import com.lyj.model.vo.RecommondData;

import java.util.List;

/**
 * Created by Administrator on 2019/7/1.
 */
public interface RecommondDataMapper {

    List<RecommondData> getRecommondData(Integer limit);


}
