package com.troyboot.java.common.utils;

import java.util.HashMap;
import java.util.Map;

public class OutMassage extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public OutMassage() {
		put("code", 1);
		put("msg", "操作成功");
	}

	public static OutMassage error() {
		return error(1, "操作失败");
	}

	public static OutMassage error(String msg) {
		return error(500, msg);
	}

	public static OutMassage error(int code, String msg) {
		OutMassage om = new OutMassage();
		om.put("code", code);
		om.put("msg", msg);
		return om;
	}

	public static OutMassage ok(String msg) {
		OutMassage om = new OutMassage();
		om.put("msg", msg);
		return om;
	}

	public static OutMassage ok(Map<String, Object> map) {
		OutMassage om = new OutMassage();
		om.putAll(map);
		return om;
	}

	public static OutMassage ok() {
		return new OutMassage();
	}

	@Override
	public OutMassage put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
