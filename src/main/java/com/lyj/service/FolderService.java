package com.lyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.dao.FolderMapper;
import com.lyj.exception.MessageException;
import com.lyj.model.Folder;
import com.lyj.model.FolderExample;
import com.lyj.model.User;
import com.lyj.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */


@Service
public class FolderService {

    @Autowired
    FolderMapper folderMapper;

    public void addFolder(Folder folder) {
        int insert = folderMapper.insertSelective(folder);
        if(insert!=1){
            throw new MessageException("文件夹添加失败");
        }
    }

    public List<Folder> getFoldersByUserId(int userId){
        FolderExample folderExample=new FolderExample();
        folderExample.createCriteria().andUserIdEqualTo(userId);
        List<Folder> list = folderMapper.selectByExample(folderExample);
        return list;
    }

    public void deleteFolder(Folder folder) {
        int i = folderMapper.deleteByPrimaryKey(folder.getId());
        if(i!=1){
            throw new MessageException("文件夹删除失败");
        }
    }

    public void updateFolderById(Folder folder) {
        int i = folderMapper.updateByPrimaryKeySelective(folder);
        if(i!=1){
            throw new MessageException("文件夹更改失败");
        }
    }
}
