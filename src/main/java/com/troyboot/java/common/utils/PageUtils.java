package com.troyboot.java.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class  PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<?> rows;
    private int code;

    public PageUtils(List<?> list, Long total) {
        this.rows = list;
        this.total = total;
        this.code = 200;
    }

}
