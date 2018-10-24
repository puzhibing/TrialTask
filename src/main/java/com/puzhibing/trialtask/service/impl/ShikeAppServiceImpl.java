package com.puzhibing.trialtask.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import com.puzhibing.trialtask.util.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.puzhibing.trialtask.pojo.shike.App;
import com.puzhibing.trialtask.pojo.shike.ResultList;
import com.puzhibing.trialtask.service.IShikeAppService;
import com.puzhibing.trialtask.util.ComUtil;
import com.puzhibing.trialtask.util.ResultUtil;


/**
 * 定义操作shike APP相关数据
 * @author asus
 *
 */
@Service
public class ShikeAppServiceImpl implements IShikeAppService {

    @Autowired
    private SMSUtil smsUtil;
    
    @Autowired
    private ComUtil comUtil;

	/*
	 * 定义通过get方法获取任务列表
	 * @see com.puzhibing.testPlayAid.service.IShikeAppService#sendGet(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultUtil getTaskList(String url, String download , String asin) {
		String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?download=" + download + "&asin=" + asin;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Host", "shike.com");
            connection.setRequestProperty("Referer", "http://shike.com/orochi/FKTryPlay?show=false");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
            connection.setRequestProperty("Accept", "application/json, text/plain, */*");
            connection.setRequestProperty("Cookie", "IORI=E8BgQJwQkdMYCow9KYcbvLV2xoKiy2qZTQ8VeDl1rNeLXLkBogPSWUz5/14LBbkZ31Vpjz+o8tjD4aMIQsDEZ/AkYALhsVCc5THUGY6iDZ4=; JSESSIONID=997D0FAAE5063EF484F4A64815339302; OD=GJGv8kGGH6uS6J3RK56H3Gi8axMaiHJKFCmtaJ89Mc79Jrj5UUo/+gjlEu8jo5JW");
            connection.setRequestProperty("Accept-Language", "zh-cn");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
            
            
            
            // 建立实际的连接
            connection.connect();
            
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            if(map.size() != 0) {
            	// 遍历所有的响应头字段
//                for (String key : map.keySet()) {
//                    System.out.println(key + "--->" + map.get(key));
//                }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(connection.getInputStream() , "UTF-8"));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            }
            
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
//        System.err.println(result);
        
        ResultList list = JSON.parseObject(result, ResultList.class);
        System.out.println("app：" + list.getData().get(0).getApp());
        System.out.println("preList：" + list.getData().get(0).getPreList());
        System.out.println("accPreList：" + list.getData().get(0).getAccPreList());
        System.out.println("redList：" + list.getData().get(0).getRedList());
        
        List<App> apps = list.getData().get(0).getApp();//获取任务集合
        float money1 = 0.0f;
        float money2 = 0.0f;
        App app = null;
        ResultUtil resultUtil = new ResultUtil();
        if(null != apps && apps.size() != 0) {
       
        	//循环遍历看是否有抢成功的
        	for (App shike : apps) {
				if(shike.isPlaying() == true) {
					resultUtil.setStatus(true);
	        		resultUtil.setImg(shike.getIcon());
	        		resultUtil.setMsg("shike");
	        		resultUtil.setInfo(shike.getTrackId());
	        		System.err.println("试客成功！");
	        		return resultUtil;
				}
			}
        	
        	//遍历出金额最大且数量大于10个的对象
        	for (int i = 0; i < apps.size(); i++) {
        		money1 = Float.valueOf(apps.get(i).getMoney()).floatValue();
    			if(money1 >= money2 && apps.get(i).getNumber() > 0) {
    				
    				//判断当前任务是否在放弃任务集合中，包含则不需要抢该任务
    				boolean b = comUtil.judgeWhetherIncludeValue("shike", apps.get(i).getTrackId());
    				if(b) {
    					continue;
    				}
    				
    				app = apps.get(i);
    			}
    		}
        }
        String status = "";
        
        if(null != app) {
        	status = this.grabTask(app);
        	if(status.contains("领取成功")) {
        		resultUtil.setStatus(true);
        		resultUtil.setImg(app.getIcon());
        		resultUtil.setMsg("shike");
        		resultUtil.setInfo(app.getTrackId());
        		System.err.println("发短信！");
                smsUtil.sendSMS("试客APP");//调用发送短信处理
        	}else {
        		resultUtil.setStatus(false);
        		resultUtil.setMsg("领取失败");
        	}
        }else {
        	resultUtil.setStatus(false);
    		resultUtil.setMsg("任务为空");
        }
        return resultUtil;
	}
	
	
	
	
	
	
	/**
	 * 定义抢任务处理方法
	 * @param app
	 * @return
	 */
	public String grabTask(App app) {
		PrintWriter out = null;
		BufferedReader in = null;
		String url = "http://shike.com/shike/api/appClick";
		String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Host", "shike.com");
            conn.setRequestProperty("Accept-Language", "zh-cn");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
            conn.setRequestProperty("Accept", "application/json, text/plain, */*");
            conn.setRequestProperty("Referer", "http://shike.com/orochi/FKTryPlay?show=false");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Cookie", "IORI=E8BgQJwQkdMYCow9KYcbvLV2xoKiy2qZTQ8VeDl1rNeLXLkBogPSWUz5/14LBbkZ31Vpjz+o8tjD4aMIQsDEZ/AkYALhsVCc5THUGY6iDZ4=; JSESSIONID=997D0FAAE5063EF484F4A64815339302; OD=GJGv8kGGH6uS6J3RK56H3Gi8axMaiHJKFCmtaJ89Mc79Jrj5UUo/+gjlEu8jo5JW");
            conn.setRequestProperty("Content-Length", "423");
            conn.setRequestProperty("Origin", "http://shike.com");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            
               
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print("sign=" + app.getSign() + "&asin=qLgKdimlcEzTNHiZTdJvd%2B%2B%2B6GQQz9WlpjYDe%2BM4%2BZ87GVf%2FYJRdh%2FFUKDzSv4rvkFDGwzFW3oz6B4YSaF6LMNQtUCBh8Suiw49XR4iOIwQBinKX%2BUjSWUffZ7dSTK3S3WgHB2VyLSt5d9yzHx%2B%2BrnkXCfAaW27%2BnCc52w%2FR7eoIHt2rB0zyT6fkilXYlpwMXGL%2BGhfxkoxGjmVC%2BvqGYMuSjjyvN5z5yXyxH2GZ%2F%2F4DBSpKZTXIL2P76ui1wzFL2UFJ0EB9HnQPdxb8Y6NSQA%3D%3D");
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
	}
}
