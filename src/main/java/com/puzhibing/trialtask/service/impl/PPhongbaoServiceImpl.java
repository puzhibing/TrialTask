package com.puzhibing.trialtask.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puzhibing.trialtask.service.IPPhongbaoService;
import com.puzhibing.trialtask.util.RandomString;
import com.puzhibing.trialtask.util.ResultUtil;


@Service
public class PPhongbaoServiceImpl implements IPPhongbaoService {

	@Autowired
	private RandomString randomString;
	
	/*
	 * 定义获取任务列表
	 * @see com.puzhibing.testPlayAid.service.IPPhongbaoService#GetTaskList(java.lang.String)
	 */
	@Override
	public ResultUtil GetTaskList() {
	
		String token = randomCode(11);
		String USERINFO = randomCode(11);
		Long datetime = System.currentTimeMillis()/1000;
		String dates = String.valueOf(datetime - 15000) + "," + String.valueOf(datetime - 9200) + "," + String.valueOf(datetime - 5400) + "," + String.valueOf(datetime);
		
		

	    //获取最新变量参数PHPSESSID和access的值
        Map<String , String> map1 = obtainVariableParameter(token , USERINFO , dates);

//        Map<String , String> map2 = login(map1.get("PHPSESSID") , map1.get("access") , dates , USERINFO);
//
//        Map<String , String> map3 = getList(map2.get("PHPSESSID") , map2.get("access"));


        return null;
	}


    /**
     * 获取原始的可变参数值
     * @param url
     * @return
     */
	public Map<String , String> obtainVariableParameter(String token , String USERINFO , String dates){
        Map<String , String> parameter = new HashMap<>();
        String path = "http://pphongbao.com/?token=";
		String data = "2ZIJRuz6wRULDA90NDk6wFbDzb3dvkj%2FXWUWUjyByev%2FNKUscxsoqzv5bgH9HSSLvBFMxzA%2BeoU4gRrznVnAo9ydm%2FQwbpq224W%2BE%2B38LqAh9Gh3dU%2Fk8bjnfLxY%2Bh3XxCizITstHcXJ1hrC0iwJiI7B17SliI1OmiSKufbAs6fN1dh7rT%2BA%2BebwCYlG8n8%2FqSY7kuiS7ZXzJqApetWgE4Raj03yL4Iz8VdysAV4e5lOwo4a8NXglpmxYJLYxEzD2N9vmdQIt8q%2F2RlV3THlIKFlbb6o5UsT8aSoGFdFlu43L1FBRHGZrrg%2FviCAw7uC";
		String suffix = "%3D";
        try {
            URL realUrl = new URL(path + data + token + suffix);
            // 打开和URL之间的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection)realUrl.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("Host", "pphongbao.com");
			httpURLConnection.setRequestProperty("Connection", "keep-alive");
			httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpURLConnection.setRequestProperty("Accept-Language", "zh-cn");
			httpURLConnection.setRequestProperty("Cookie", "Hm_lvt_e91800291a5741f043ef7d78a2580f0b=" + dates + "; code=981354385; fast_s_n=1; ppport=10091; scheme=cn.run.letsrun; 1c6abf7a3ce9f84965344760d015a2ae=1; USERINFO=" + data + USERINFO + suffix + "; _cb=1; access=VABQAF5TW1IAAwVWDFYOUA%3D%3D");
			httpURLConnection.setRequestProperty("Connection", "keep-alive");
			httpURLConnection.setRequestProperty("DNT", "1");
			httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			
            // 建立实际的连接
			httpURLConnection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            if(map.size() != 0) {
            	
            	// 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
            	
//                List<String> list = map.get("Set-Cookie");
//                String phpsessid = "";
//                String access = "";
//                for (String str : list) {
//                    if(str.contains("PHPSESSID")){
//                        phpsessid = str.substring(str.indexOf("=") + 1 , str.indexOf(";"));
//                    }
//
//                    if(str.contains("access")){
//                        access = str.substring(str.indexOf("=") + 1 , str.indexOf(";"));
//                    }
//                }
//                parameter.put("PHPSESSID" ,phpsessid);
//                parameter.put("access" ,access);
            }
            
            //第一次重定向
            String location1 = httpURLConnection.getHeaderField("Location");
            URL url1 = new URL(location1);
            HttpURLConnection httpURLConnection1 = (HttpURLConnection)url1.openConnection();
            httpURLConnection1.setInstanceFollowRedirects(false);
            httpURLConnection1.connect();
            Map<String, List<String>> map1 = httpURLConnection1.getHeaderFields();
            if(map1.size() != 0) {
            	
            	// 遍历所有的响应头字段
                for (String key : map1.keySet()) {
                    System.out.println(key + "--->" + map1.get(key));
                }
            }
            
            
            //第二次重定向
            String location2 = httpURLConnection1.getHeaderField("Location");
            URL url2 = new URL(location2);
            HttpURLConnection httpURLConnection2 = (HttpURLConnection)url2.openConnection();
            httpURLConnection2.setInstanceFollowRedirects(false);
            httpURLConnection2.connect();
            Map<String, List<String>> map2 = httpURLConnection2.getHeaderFields();
            if(map2.size() != 0) {
            	
            	// 遍历所有的响应头字段
                for (String key : map2.keySet()) {
                    System.out.println(key + "--->" + map2.get(key));
                }
            }
            
            
          //第三次重定向
            String location3 = httpURLConnection2.getHeaderField("Location");
            URL url3 = new URL(location3);
            HttpURLConnection httpURLConnection3 = (HttpURLConnection)url3.openConnection();
            httpURLConnection3.setInstanceFollowRedirects(false);
            httpURLConnection3.connect();
            Map<String, List<String>> map3 = httpURLConnection3.getHeaderFields();
            if(map3.size() != 0) {
            	
            	// 遍历所有的响应头字段
                for (String key : map3.keySet()) {
                    System.out.println(key + "--->" + map3.get(key));
                }
            }
            
            

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }

        return parameter;
    }


    /**
     * 成功登录并返回信息的参数值
     * @param p
     * @param a
     * @return
     */
    public Map<String , String> login(String p , String a , String dates , String USERINFO){
        Map<String , String> parameter = new HashMap<>();
        String path = "http://pphongbao.com/";
        String data = "2ZIJRuz6wRULDA90NDk6wFbDzb3dvkj%2FXWUWUjyByev%2FNKUscxsoqzv5bgH9HSSLvBFMxzA%2BeoU4gRrznVnAo9ydm%2FQwbpq224W%2BE%2B38LqAh9Gh3dU%2Fk8bjnfLxY%2Bh3XxCizITstHcXJ1hrC0iwJiI7B17SliI1OmiSKufbAs6fN1dh7rT%2BA%2BebwCYlG8n8%2FqSY7kuiS7ZXzJqApetWgE4Raj03yL4Iz8VdysAV4e5lOwo4a8NXglpmxYJLYxEzD2N9vmdQIt8q%2F2RlV3THlIKFlbb6o5UsT8aSoGFdFlu43L1FBRHGZrrg%2FviCAw7uC";
		String suffix = "%3D";
        try {
            URL url = new URL(path);
            // 打开和URL之间的连接
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setInstanceFollowRedirects(false);
            // 设置通用的请求属性
            httpURLConnection.setRequestProperty("Host", "pphongbao.com");
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            httpURLConnection.setRequestProperty("Accept-Language", "zh-cn");
            httpURLConnection.setRequestProperty("Cookie", "Hm_lvt_e91800291a5741f043ef7d78a2580f0b=" + dates + "; code=981354385; fast_s_n=1; go-task-list=1; ppport=10091; scheme=cn.run.letsrun; 1c6abf7a3ce9f84965344760d015a2ae=1; PHPSESSID=" + p + "USERINFO=" + data + USERINFO + suffix + "; _cb=1; access=" + a);
            httpURLConnection.setRequestProperty("Connection", "keep-alive");
            httpURLConnection.setRequestProperty("DNT", "1");
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");

            
            // 建立实际的连接
            httpURLConnection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            if(map.size() != 0) {
            	System.out.println(map.size());
            	// 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
            	
                List<String> list = map.get("Set-Cookie");
                String access = "";
                for (String str : list) {
                    if(str.contains("access")){
                        access = str.substring(str.indexOf("=") + 1 , str.indexOf(";"));
                    }
                }
                parameter.put("access" ,access);
            }

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
	    return parameter;
    }


    /**
     * 获取任务列表
     * @param p
     * @param a
     * @return
     */
    public Map<String , String> getList(String p , String a){
        Map<String , String> parameter = new HashMap<>();
        try {
            String url = "http://pphongbao.com/index.php/fasttask/index";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Host", "pphongbao.com");
            connection.setRequestProperty("Accept-Language", "zh-cn");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
            connection.setRequestProperty("Accept-Cache", "");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            connection.setRequestProperty("Accept", "*/*");
            connection.setRequestProperty("Referer", "http://pphongbao.com/");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cookie", "Hm_lvt_e91800291a5741f043ef7d78a2580f0b=1538058674; code=981354385; fast_s_n=1; ppport=10091; 1c6abf7a3ce9f84965344760d015a2ae=1; PHPSESSID=fgme5ss3epp78qfh71r9frenj3; USERINFO=2ZIJRuz6wRULDA90NDk6wFbDzb3dvkj%2FXWUWUjyByev%2FNKUscxsoqzv5bgH9HSSLvBFMxzA%2BeoU4gRrznVnAo9ydm%2FQwbpq224W%2BE%2B38LqAh9Gh3dU%2Fk8bjnfLxY%2Bh3XxCizITstHcXJ1hrC0iwJiI7B17SliI1OmiSKufbAs6fN1dh7rT%2BA%2BebwCYlG8n8%2FqSY7kuiS7ZXzJqApetWgE4Raj03yL4Iz8VdysAV4e5lOwo4a8NXglpmxYJLYxEzD2N9vmdQIt8q%2F2RlV3THlIKFlbb6o5UsT8aSoGFdFlu43L1FBRHGZruUF3R53yJs4Y01zfGCjVDg%3D; _cb=1; access=AgADAg0GDlRYDlQFA1oAUw%3D%3D");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("DNT", "1");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");


            // 建立实际的连接
            connection.connect();

            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            //获取可变参数值
//            parameter = getParameter(map);

        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        return parameter;
    }



    
    //获取随机码
    public String randomCode(int num) {
		String basicData = "1DEFst4ABCopG5JKLOXaP23QrwRvUVyS0Tu6nqYZMNklmx7Wbc9defHIghij8z";
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < num; i++) {
			Random random = new Random();
			
			stringBuffer.append(basicData.charAt(random.nextInt(basicData.length())));
		}
		System.err.println(stringBuffer.toString());
		return stringBuffer.toString();
	}

}
