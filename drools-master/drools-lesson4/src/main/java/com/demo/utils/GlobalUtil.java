package com.demo.utils;

/**
 * @author sec
 * @version 1.0
 * @date 2020/3/16 4:23 PM
 **/
public class GlobalUtil {

	Integer integer = new Integer(2);
	private static int count = 0;

	public static void add() {
		count++;
		System.out.println("GlobalUtil.add()---count=" + count);
	}

	public static int getCount() {
		return count;
	}
}
