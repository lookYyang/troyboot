package com.troyboot.java.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

import static com.sun.activation.registries.LogSupport.log;

/**
 * @Authour YangYang
 * @Date 2018/6/7 10:24
 */
@Slf4j
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

    /**
     * 返回去除连接符-的UUID
     *
     * @return
     */
    public String uuid() {
        String uuid = rawUuid();
        return uuid.replaceAll("-", "");
    }

    /**
     * 返回原生UUID
     *
     * @return
     */
    public String rawUuid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 判断对象是否Empty(null或元素为0)<br>
     * 实用于对如下对象做判断:String Collection及其子类 Map及其子类
     *
     * @param pObj
     *            待检查对象
     * @return boolean 返回的布尔值
     */
    public static boolean isEmpty(Object pObj) {
        if (pObj == null)
            return true;
        if (pObj == "")
            return true;
        if (pObj instanceof String) {
            if (((String) pObj).length() == 0) {
                return true;
            }
        } else if (pObj instanceof Collection) {
            if (((Collection) pObj).size() == 0) {
                return true;
            }
        } else if (pObj instanceof Map) {
            if (((Map) pObj).size() == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成树路径ID，如：01.01.01
     *
     * @param curMaxNode 本级当前最大节点ID，如果要生成本级第一个节点则传XX.XX.00(XX.XX为父节点ID)。
     * @param maxValue   本级节点ID允许的最大值
     * @return
     */
    public static String genCascadeTreeId(String curMaxNode, int maxValue) {
        String prefix = StringUtils.substringBeforeLast(curMaxNode, ".");
        String last = StringUtils.substringAfterLast(curMaxNode, ".");
        if (isEmpty(last)) {
            log("树ID生成器生成节点ID参数错误");
        }
        int intLast = Integer.valueOf(last);
        if (intLast == maxValue || intLast > maxValue) {
            log("树ID生成器本级节点号源用尽");
        }
        String thisNode = String.valueOf(intLast + 1);
        thisNode = StringUtils.leftPad(thisNode, String.valueOf(maxValue).length(), "0");
        return prefix + "." + thisNode;
    }

    /**
     * 获取IP地址
     *
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
