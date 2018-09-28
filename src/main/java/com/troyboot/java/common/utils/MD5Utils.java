package com.troyboot.java.common.utils;

import com.troyboot.java.system.common.Constant;
import org.apache.shiro.crypto.hash.SimpleHash;

public class MD5Utils {

	public static String encrypt(String username, String pswd) {
		return new SimpleHash(Constant.ALGORITH_NAME, pswd, Constant.PWD_SALT + username, Constant.HASH_ITERATIONS).toHex();
	}

	public static void main(String arg[]){
		System.out.println(MD5Utils.encrypt("test", "123456"));
	}
}
