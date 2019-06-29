package com.lyj.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyj.dao.UserMapper;
import com.lyj.exception.MessageException;
import com.lyj.model.Folder;
import com.lyj.model.User;
import com.lyj.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    FolderService folderService;


    public PageInfo<User> getUsers(int page,int limit){
        PageHelper.startPage(page, limit);
        List<User> userList = userMapper.selectByExample(new UserExample());
        return new PageInfo<>(userList);
    }


    public void addUser(User user){
        int insert = userMapper.insertSelective(user);
        if(insert!=1){
            throw new MessageException("用户注册失败");
        }

        Folder folder=new Folder();
        folder.setName("默认文件夹");
        folder.setPid(0);
        folder.setUserId(user.getId());
        folderService.addFolder(folder);

    }

    public User userLogin(User user){
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUserNameEqualTo(user.getUserName())
                .andPasswordEqualTo(user.getPassword());
        List<User> userList = userMapper.selectByExample(userExample);
        if(userList.size()!=1){
            throw new MessageException("用户名或密码错误");
        }

        return userList.get(0);
    }

    public User getUserById(int userId){
        return userMapper.selectByPrimaryKey(userId);
    }

}
