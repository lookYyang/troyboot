package com.troyboot.java.common.utils;

import java.io.Serializable;
import java.util.List;

public class  PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long total;
    private List<?> rows;
    private int appcode;

    public PageUtils(List<?> list, Long total) {
        this.rows = list;
        this.total = total;
        this.appcode = 200;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getAppcode() {
        return appcode;
    }

    public void setAppcode(int appcode) {
        this.appcode = appcode;
    }

}
