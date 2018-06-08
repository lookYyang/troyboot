package com.troyboot.java.common.utils;

import java.util.Collection;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:24
 */
public class CommonUtils {

    /**
     * 逗号连接字符串
     */
    public static String join(Collection<String> strs) {
        StringBuilder sb = new StringBuilder();
        for(String str:strs){
            if(sb.length()>0) sb.append(",");
            sb.append(str);
        }
        return sb.toString();
    }

}
