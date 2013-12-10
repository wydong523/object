package com.google.zxing.client.android.vo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 应用缓存对象
 * 
 * @author 汪渝栋
 * 
 */
public class Cache implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2471151629742287542L;
	public static Cache cache = null;

	private Cache() {
	}

	public static synchronized Cache getInstance() {
		if (cache == null) {
			cache = new Cache();
		}
		return cache;
	}

	// 工单缓存
	private static ArrayList<String> cacheWorkorders = new ArrayList<String>();

	public ArrayList<String> getCacheWorkorders() {
		return cacheWorkorders;
	}

}
