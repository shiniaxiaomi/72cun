package com.lyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.dao.UrlMapper;
import com.lyj.dao.vo.UrlExtendsMapper;
import com.lyj.exception.MessageException;
import com.lyj.model.Url;
import com.lyj.model.UrlExample;
import com.lyj.model.vo.UrlExtends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */

@Service
public class UrlService {

    @Autowired
    UrlMapper urlMapper;//网址数据

    @Autowired
    UrlExtendsMapper urlExtendsMapper;//推荐数据


    public PageInfo<Url> getUrls(int userId, int page, int limit) {
        PageHelper.startPage(page, limit);
        UrlExample urlExample = new UrlExample();
        urlExample.createCriteria().andUserIdEqualTo(userId);
        urlExample.setOrderByClause("createTime desc");//按照创建时间排序
        List<Url> urls = urlMapper.selectByExample(urlExample);
        return new PageInfo<>(urls);
    }


    public PageInfo<Url> search(int userId, String keyword, int page, int limit) {
        PageHelper.startPage(page, limit);
        UrlExample urlExample = new UrlExample();
        urlExample.createCriteria().andLabelLike("%" + keyword + "%").andUserIdEqualTo(userId);
        urlExample.setOrderByClause("createTime desc");//按照创建时间排序
        List<Url> urls = urlMapper.selectByExample(urlExample);
        return new PageInfo<>(urls);
    }

    public void updateUrlById(Url url) {
        int i = urlMapper.updateByPrimaryKeySelective(url);
        if (i != 1) {
            throw new MessageException("网址更新失败！");
        }
    }

    public void deleteUrlById(int id) {
        int i = urlMapper.deleteByPrimaryKey(id);
        if (i != 1) {
            throw new MessageException("网址删除失败！");
        }
    }

    public PageInfo<Url> getUrlsByFolderId(int pid, int page, int limit) {
        PageHelper.startPage(page, limit);
        UrlExample urlExample = new UrlExample();
        urlExample.createCriteria().andPidEqualTo(pid);
        List<Url> urls = urlMapper.selectByExample(urlExample);
        return new PageInfo<>(urls);
    }

    public PageInfo<Url> searchInFolder(int userId,String keyword, int pid, int page, int limit) {
        PageHelper.startPage(page, limit);
        UrlExample urlExample = new UrlExample();
        urlExample.createCriteria().andPidEqualTo(pid)
                .andLabelLike("%" + keyword + "%").andUserIdEqualTo(userId);
        urlExample.setOrderByClause("createTime desc");//按照创建时间排序

        List<Url> urls = urlMapper.selectByExample(urlExample);
        return new PageInfo<>(urls);
    }

    public void addUrl(Url url) {
        int i = urlMapper.insertSelective(url);
        if (i != 1) {
            throw new MessageException("网址添加失败！");
        }
    }

    //-----以下是首页的一些请求----
    public PageInfo getRecommondData(int limit) {
        List<UrlExtends> recommondData = urlExtendsMapper.getRecommondData(limit);
        return new PageInfo<>(recommondData);
    }

    public PageInfo<UrlExtends> getSearchData(String keyword, int page, int limit) {
        PageHelper.startPage(page, limit);
        List<UrlExtends> searchData = urlExtendsMapper.getSearchData("%"+keyword+"%");
        return new PageInfo<>(searchData);
    }
}
