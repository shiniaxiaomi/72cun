package com.lyj.controller;

import com.lyj.model.Folder;
import com.lyj.model.User;
import com.lyj.service.FolderService;
import com.lyj.util.Message;
import com.lyj.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2019/4/29.
 */

@RestController
@RequestMapping("/folder")
public class FolderController {


    @Autowired
    FolderService folderService;


    @RequestMapping("/addFolder")
    public Message addFolder(Folder folder){
        folderService.addFolder(folder);

        return MessageUtil.success("文件夹添加成功！");
    }

    @RequestMapping("/deleteFolder")
    public Message deleteFolder(Folder folder){
        folderService.deleteFolder(folder);

        return MessageUtil.success("文件夹删除成功！");
    }

    @RequestMapping("/getFoldersByUserId")
    public List<Folder> getFoldersByUserId(HttpSession session){
        User user = (User) session.getAttribute("user");
        return folderService.getFoldersByUserId(user.getId());
    }

    @RequestMapping("/updateFolderById")
    public Message updateFolderById(Folder folder){
        folderService.updateFolderById(folder);
        return MessageUtil.success("文件夹更改成功！");
    }




}
