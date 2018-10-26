package com.puzhibing.trialtask.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


/**
 * 定义一个公共的工具类
 * @author asus
 *
 */
@Component
public class ComUtil {
	

	private Map<String, List<String>> map;

	public ComUtil(){
		map = new HashMap<>();
		map.put("shike", new ArrayList<>());
		map.put("xiaozhu", new ArrayList<>());
	}
	
	/**
	 * 添加数据
	 * @param key
	 * @param value
	 */
	public void putValueInMap(String key , String value) {
		List<String> list = map.get(key);
		list.add(value);
	}
	
	
	/**
	 * 判断是否包含给定值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean judgeWhetherIncludeValue(String key , String value) {
		boolean b = false;
		List<String> list = map.get(key);
		if(null != list) {
			for (String v : list) {
				if(v.equals(value)) {
					b = true;
				}
			}
		}
		
		return b;
	}
}
