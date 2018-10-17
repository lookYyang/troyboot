package com.troyboot.java.common;

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
     * 密码加盐
     */
    public static final String PWD_SALT = "doubleY";

    /**
     * 加密方式
     */
    public static final String ALGORITH_NAME = "md5";

    /**
     * 加密次数
     */
    public static final int HASH_ITERATIONS = 2;

    /**
     * 默认分页：15
     */
    public static final int DEAFULT_PAGE_LIMIT = 15;

}
