package com.lyj.dao.vo;

import com.lyj.model.vo.AttentionExtends;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2019/7/1.
 */
public interface AttentionExtendsMapper {

    List<AttentionExtends> getFans(@Param("userId")int userId);

    List<AttentionExtends> getAttention(@Param("userId") int userId);


    List<Integer> getAttentionUsers(int userId);
}
