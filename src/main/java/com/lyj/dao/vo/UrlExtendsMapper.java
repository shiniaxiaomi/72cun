package com.lyj.dao.vo;

import com.lyj.model.vo.UrlExtends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/7/1.
 */
public interface UrlExtendsMapper {

    List<UrlExtends> getRecommondData(int limit);

    List<UrlExtends> getAttentionData(@Param("followUserIds") List followUserIds);

    List<UrlExtends> getHotData();

    //isAll为true时表示查询所有数据，为false时查询共享数据
    List<UrlExtends> getSearchData(@Param("keyword")String keyword,@Param("isAll") boolean isAll);

    List<UrlExtends> getUserLikes(@Param("userId")int userId,@Param("state") int state);

    List<UrlExtends> getThisUserLikes(@Param("likesUserId")int likesUserId,@Param("state") int state);
}
