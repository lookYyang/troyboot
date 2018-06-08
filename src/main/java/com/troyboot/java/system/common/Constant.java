package com.troyboot.java.system.common;

/**
 * @Authour YangYang
 * @Date 2018/6/7 1:32
 */
public class Constant {

    /**
     * 通用的是否布尔值
     */
    public static enum YesOrNo {

        NO(0, "否"), YES(1, "是");

        private YesOrNo(Integer value, String name) {
            this.value = value;
            this.name = name;
        }

        private final Integer value;
        private final String name;

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * 缺省的密码字段加解密钥
     */
    public static final String PWD_KEY = "HY&XC&MW";

}
