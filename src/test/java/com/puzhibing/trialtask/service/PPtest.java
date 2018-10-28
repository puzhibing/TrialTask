package com.puzhibing.trialtask.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.junit.Test;



public class PPtest {

	
	
	@Test
	public void test() {
		String token = "2ZIJRuz6wRULDA90NDk6wFbDzb3dvkj/XWUWUjyByev/NKUscxsoqzv5bgH9HSSLvBFMxzA+eoU4gRrznVnAo9ydm/Qwbpq224W+E+38LqAh9Gh3dU/k8bjnfLxY+h3XxCizITstHcXJ1hrC0iwJiI7B17SliI1OmiSKufbAs6fN1dh7rT+A+ebwCYlG8n8/qSY7kuiS7ZXzJqApetWgE4Raj03yL4Iz8VdysAV4e5lOwo4a8NXglpmxYJLYxEzD2N9vmdQIt8q/2RlV3THlIKFlbb6o5UsT8aSoGFdFlu43L1FBRHGZruFXilD9A+JTp72ZEw2sed8=";
		Long datetime = System.currentTimeMillis()/1000;
		String dates = String.valueOf(datetime - 15000) + "," + String.valueOf(datetime - 9200) + "," + String.valueOf(datetime - 5400) + "," + String.valueOf(datetime);
		String USERINFO = "2ZIJRuz6wRULDA90NDk6wFbDzb3dvkj/XWUWUjyByev/NKUscxsoqzv5bgH9HSSLvBFMxzA+eoU4gRrznVnAo9ydm/Qwbpq224W+E+38LqAh9Gh3dU/k8bjnfLxY+h3XxCizITstHcXJ1hrC0iwJiI7B17SliI1OmiSKufbAs6fN1dh7rT+A+ebwCYlG8n8/qSY7kuiS7ZXzJqApetWgE4Raj03yL4Iz8VdysAV4e5lOwo4a8NXglpmxYJLYxEzD2N9vmdQIt8q/2RlV3THlIKFlbb6o5UsT8aSoGFdFlu43L1FBRHGZrhGv3xMUxdUKYYBeq3b7FLU=";
		Map<String, String> resultMap= getLoginData(token , dates , USERINFO);
		getTaskList(dates , resultMap.get("PHPSESSID") , USERINFO , resultMap.get("access"));
	}
	
	
	
	
	
	public Map<String, String> getLoginData(String token , String dates , String USERINFO) {
		Map<String, String> resultMap = new HashMap<>();
		String path = "http://pphongbao.com/?token=" + token;
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setInstanceFollowRedirects(false);
			httpURLConnection.setRequestProperty("Host", "pphongbao.com");
			httpURLConnection.setRequestProperty("Connection", "keep-alive");
			httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpURLConnection.setRequestProperty("Accept-Language", "zh-cn");
			httpURLConnection.setRequestProperty("Cookie", "Hm_lvt_e91800291a5741f043ef7d78a2580f0b=" + dates + "; code=981354385; fast_s_n=1; open_app=49D/jfsqkwHvlt48iyqG20zrlVTb+hKWqUsdPjIBR2c=; ppport=10091; scheme=cn.run.letsrun; 1c6abf7a3ce9f84965344760d015a2ae=1; USERINFO=" + USERINFO + "; _cb=1; access=VABQAF5TW1IAAwVWDFYOUA==");
			httpURLConnection.setRequestProperty("Connection", "keep-alive");
			httpURLConnection.setRequestProperty("DNT", "1");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			
			httpURLConnection.connect();
			// 获取所有响应头字段
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            if(map.size() != 0) {
            	
            	// 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                   
                    if(key != null && key.equals("Set-Cookie")) {
                    	List<String> vs = map.get(key);
                    	for (String v : vs) {
							if(v.contains("PHPSESSID")) {
								v = v.substring(v.indexOf("=") + 1, v.indexOf(";"));
//								System.err.println(v);
								resultMap.put("PHPSESSID", v);
							}
							if(v.contains("access")) {
								v = v.substring(v.indexOf("=") + 1, v.indexOf(";"));
//								System.err.println(v.replaceAll("%3D", "="));
								resultMap.put("access", v.replaceAll("%3D", "="));
							}
						}
                    	
                    }
                }
            }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultMap;
		
	}
	
	
	
	
	
	public Map<String, String> getTaskList(String dates , String PHPSESSID , String USERINFO , String access) {
		Map<String, String> resultMap = new HashMap<>();
		String path = "http://pphongbao.com/";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setRequestProperty("Host", "pphongbao.com");
			httpURLConnection.setRequestProperty("Connection", "keep-alive");
			httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpURLConnection.setRequestProperty("Accept-Language", "zh-cn");
			httpURLConnection.setRequestProperty("Cookie", "Hm_lvt_e91800291a5741f043ef7d78a2580f0b=" + dates + "; code=981354385; fast_s_n=1; go-task-list=1; open_app=49D/jfsqkwHvlt48iyqG20zrlVTb+hKWqUsdPjIBR2c=; ppport=10091; scheme=cn.run.letsrun; 1c6abf7a3ce9f84965344760d015a2ae=1; PHPSESSID=" + PHPSESSID + "; USERINFO=" + USERINFO + "; _cb=1; access=" + access);
			httpURLConnection.setRequestProperty("Connection", "keep-alive");
			httpURLConnection.setRequestProperty("DNT", "1");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			
			httpURLConnection.connect();
			// 获取所有响应头字段
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            if(map.size() != 0) {
            	
            	// 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.err.println(key + "--->" + map.get(key));
                   
//                    if(key != null && key.equals("Set-Cookie")) {
//                    	List<String> vs = map.get(key);
//                    	for (String v : vs) {
//							if(v.contains("PHPSESSID")) {
//								v = v.substring(v.indexOf("=") + 1, v.indexOf(";"));
//								System.err.println(v);
//								resultMap.put("PHPSESSID", v);
//							}
//							if(v.contains("access")) {
//								v = v.substring(v.indexOf("=") + 1, v.indexOf(";"));
//								System.err.println(v.replaceAll("%3D", "="));
//								resultMap.put("access", v.replaceAll("%3D", "="));
//							}
//						}
//                    	
//                    }
                }
                
                
                //处理内容
                GZIPInputStream gzipInputStream = new GZIPInputStream(httpURLConnection.getInputStream());
                byte[] b = new byte[1024];
                int i;
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while((i = gzipInputStream.read(b)) != -1) {
                	out.write(b, 0, i);
                }
                
                System.out.println(out.toString());
            }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultMap;
		
	}
}
