package com.example.homework1.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Result<T> {
    //    是否成功
    private Boolean success;
    //    状态码
    private Integer code;
    //    返回消息
    private String message;
    //    总条数
    private long count;
    //    返回数据
    private T data;


    private Result(){

    }

    /**
     * 执行成功，不返回数据
     * @return
     * @param <T>
     */
    public static<T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(Resultcode.SUCCESS);
        result.setMessage("执行成功");
        return result;
    }

    /**
     * 带数据返回，成功
     * @param data 数据
     * @return
     * @param <T>
     */
    public static<T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(Resultcode.SUCCESS);
        result.setMessage("执行成功");
        result.setData(data);
        return result;
    }

    /**
     * 带数据返回，成功(分页)
     *
     * @param data 数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(long count, T data) {

        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(Resultcode.SUCCESS);
        result.setMessage("执行成功");
        result.setCount(count);
        result.setData(data);
        return result;
    }

    /**
     * 执行失败
     *
     */
    public static <T> Result<T> error(){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(Resultcode.ERROR);
        result.setMessage("执行失败");
        return result;
    }
    public static <T> Result<T> error(String msg){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(Resultcode.ERROR);
        result.setMessage(msg);
        return result;
    }

    /**
     * 设置是否成功
     * @param success
     * @return
     */
    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置状态码
     * @param code
     * @return
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

}
