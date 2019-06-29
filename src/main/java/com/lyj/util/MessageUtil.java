package com.lyj.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by 陆英杰
 * 2018/9/27 0:38
 */
public class MessageUtil {

    private static Log logger = LogFactory.getLog(MessageUtil.class);

    public static Message success(String message){
        return success(message,null);
    }
    public static Message success(Object data){
        return new Message(Message.SUCCESS,"成功",data);
    }
    public static Message success(String message,Object data){
        return new Message(Message.SUCCESS,message,data);
    }

    public static Message error(String message){
        return error(message,null);
    }
    public static Message error(Object data){
        logger.error("失败:"+data);
        return new Message(Message.ERROR,"失败",data);
    }
    public static Message error(String message,Object data){
        logger.error(message);
        return new Message(Message.ERROR,message,data);
    }
    public static Message error(String message,Integer code,Object data){
        logger.error(message);
        return new Message(code,message,data);
    }

}
