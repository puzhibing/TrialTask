package com.puzhibing.trialtask.util;

import java.util.Random;

import org.springframework.stereotype.Component;


/**
 * 定义生成随机字符串
 * @author asus
 *
 */
@Component
public class RandomString {
	private String[] arr = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	public String getRandomString() {
		Random random = new Random();
		StringBuffer str = new StringBuffer(); 
		for(int j = 0 ; j < 22 ; j++) {
			str.append(arr[random.nextInt(52)]);
		}
		System.err.println(str.toString());
		return str.toString();
	}
}
