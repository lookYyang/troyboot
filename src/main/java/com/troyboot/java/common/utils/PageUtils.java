package com.troyboot.java.common.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author yang
 */
@Getter
@Setter
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

}
