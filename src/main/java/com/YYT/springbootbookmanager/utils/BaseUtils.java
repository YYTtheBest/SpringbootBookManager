package com.YYT.springbootbookmanager.utils;

import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.context.annotation.Bean;

public class BaseUtils {

    /**
     * 自动判断mapper成功与否返回相应内容
     *
     * @param b 布尔值
     * @param s 内容，可为null
     * @return Result结果
     */
    public static Result OkError(boolean b, @DefaultValue("null") String s) {

        if (s != null) {
            if (b) {
                return Result.ok(s);
            } else {
                return Result.error();
            }
        } else {
            if (b) {
                return Result.ok();
            } else {
                return Result.error();
            }
        }


    }
}
