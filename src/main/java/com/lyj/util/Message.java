package com.lyj.util;

public class Message<T> {

    public static Integer SUCCESS=0;
    public static Integer ERROR=1;
    public static Integer REFUND_ERROR=-1;//支付宝退款失败


    //错误码
    private Integer code;
    //信息
    private String message;
    //具体内容
    private T data;

    public Message(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Message() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

}
