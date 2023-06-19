package com.shopping.commons;

import lombok.Getter;

/**
 * 结果处理类
 */
@Getter
public class Result {
    private Boolean success;
    private Integer code;
    private String message;
    private Object data;

    private Result(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(){
        return new Result(true,200,null,null);
    }


    public static Result success(String message){
        return new Result(true,200,message,null);
    }

    public static Result success(String message,Object data){
        return new Result(true,200,message,data);
    }


    public static Result fail(int code,String message,Object data){
        return new Result(false,code,message,data);
    }


    public static Result fail(int code,String message){
        return new Result(false,code,message,null);
    }


    public static Result fail(int code){
        return new Result(false,code,null,null);
    }





}
