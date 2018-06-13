package com.troyboot.java.common.utils;

import java.util.HashMap;
import java.util.Map;

public class OutMessage extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public OutMessage() {
		put("code", 1);
		put("msg", "操作成功");
	}

	public static OutMessage error() {
		return error(1, "操作失败");
	}

	public static OutMessage error(String msg) {
		return error(500, msg);
	}

	public static OutMessage error(int code, String msg) {
		OutMessage om = new OutMessage();
		om.put("code", code);
		om.put("msg", msg);
		return om;
	}

	public static OutMessage ok(String msg) {
		OutMessage om = new OutMessage();
		om.put("msg", msg);
		return om;
	}

	public static OutMessage ok(Map<String, Object> map) {
		OutMessage om = new OutMessage();
		om.putAll(map);
		return om;
	}

	public static OutMessage ok() {
		return new OutMessage();
	}

	@Override
	public OutMessage put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
