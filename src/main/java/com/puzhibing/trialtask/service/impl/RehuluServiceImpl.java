package com.puzhibing.trialtask.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.puzhibing.trialtask.pojo.xiaozhu.Task;
import com.puzhibing.trialtask.service.RehuluService;
import com.puzhibing.trialtask.util.ResultUtil;


@Service
public class RehuluServiceImpl implements RehuluService {

	
	
	/**
	 * 进入首页
	 */
	@Override
	public ResultUtil loginHome() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = simpleDateFormat.format(new Date());
		Long datetime = System.currentTimeMillis()/1000;
		String dates = String.valueOf(datetime - 20000) + "," + String.valueOf(datetime - 15000) + "," + String.valueOf(datetime - 10000) + "," + String.valueOf(datetime - 5000);
		
		String path = "http://m.rehulu.com/home";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.addRequestProperty("Host", "m.rehulu.com");
			httpURLConnection.addRequestProperty("Referer", "http://m.rehulu.com/apptask/list");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept-Language", "zh-cn");
			httpURLConnection.addRequestProperty("Cookie", "cer=appstore.com.qingfengxulai; feedback=1; isShowTask=1; ranking_tip=" + newDate + "; sessionid=\".eJxlVF1v2jAU_SuTn0lqO3Fi542lsLG1tCp0H9WkysQ3YBGcLB9UXbX_vmsotNJeItm-Pj7n3HPzQr5PZj9m88dxnt_cz5ePi8liMbuZP36d_FyQ7IU0LbjeFnDb4odkLKQjMjRG97C0O9wgnDIZMBpw_oFFGRVZnJIRWdn1te56aElW6qqDEbFub3vIa-MvrZp6l_eXWKgr2-jn-w7auT7gMSG5jETEuMcprbPdZg5PS91t8fkR-f1bm511Z1wD-wMzYm83tYN4xPCaqz3nRa_7oSMZUl4P1mDNVTx7-HTLVTBJlAzij1EeSJWmQZRPmKTT6XRCowN7Z2aNJyN5yGIVShUySvFkdxB1B0-6RTzGvR29bfJNbTs4vNSCrl6l_BpkvOL45cr8GgRLhRe8171u79sKCzZ932QXF3a3DlvYDNUQFvXu4lhxsdMVPBoo9VD1YePW3o22_gPuuluTzA1VhXS8Kv9oXcG00uvD4shxhic85pGS_GTS8rk5cnyCYqP7mwacrzpCtXW9tcfjrquX9Rbc6cjZYvtOUrLyknSCkjSNBDaLJt4b2Glbne50b95XyGfcNJedJ8swLglNI84SDFJ35PmKgbc24B42g3ZfhoV-63HjO_tfNqwpNW6muVCJmoogEiwPYkllMFZJHqjJpfooeM7HYx-JdauduT5bc3bhGqGfcR2q1Ley2EKPoIabAkSJyQZNgzgRUbASSRwoFqlUg2Cl4Aja6K57qtuziQV2__1kpAGNA8o-cJ4xhjLJqRU33Tc_G0SFUehTMZhDPvFVnUgObGWkZNy7C3zFVMlFwaIkBqU0L9PE6wFnPMS54Vewh-q4xqE5RVSEAmemLXDuDpl77c6bz5O3EW3BNLrY5vXg0AP6d0TuPl-d_wizSyRYKJWwoigMoyrmiWFSJ7xcsVIDlQoU-fsPzsNLRQ:1gEvFL:iSiBi8bcwsQrvGNGBfsot49gbQI\"; CERT_COOKIE=appstore.qingfengxulai; CERT_TOKEN=d2dce5f8-1ea0-4653-b564-91397ae51f52; Hm_lpvt_73586d9456385c1f6e1b51968afe865d=" + String.valueOf(datetime) + "; Hm_lvt_73586d9456385c1f6e1b51968afe865d=" + dates + "; cer=appstore.; zb1023=1");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Cache-Control", "max-age=0");
			httpURLConnection.addRequestProperty("DNT", "1");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			
			httpURLConnection.connect();
			
			// 获取所有响应头字段
            Map<String, List<String>> map = httpURLConnection.getHeaderFields();
            List<String> list = new ArrayList<>();
            if(map.size() != 0) {
            	//遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                    if(key != null && key.contains("Set-Cookie")) {
                    	list = map.get(key);
                    }
                }
            }
            
            Map<String, String> resultMap = new HashMap<>();
            for (String str : list) {
            	if(str.contains("CERT_TOKEN")) {
					resultMap.put("CERT_TOKEN", str.substring(str.indexOf("=") + 1, str.indexOf(";")));
				}
            	
				if(str.contains("sessionid")) {
					resultMap.put("sessionid", str.substring(str.indexOf("=") + 1, str.indexOf(";")).replaceAll("\"", ""));
				}
			}
            
            getTaskList(resultMap);
        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//获取任务列表
	public void getTaskList(Map<String, String> map) {
		String CERT_TOKEN = map.get("CERT_TOKEN");
		String sessionid = map.get("sessionid");
		Long datetime = System.currentTimeMillis()/1000;
		String dates = String.valueOf(datetime - 20000) + "," + String.valueOf(datetime - 15000) + "," + String.valueOf(datetime - 10000) + "," + String.valueOf(datetime - 5000);
		
		String path = "http://m.rehulu.com/apptask/list";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.addRequestProperty("Host", "m.rehulu.com");
			httpURLConnection.addRequestProperty("Referer", "http://m.rehulu.com/home");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpURLConnection.addRequestProperty("Accept-Language", "zh-cn");
			httpURLConnection.addRequestProperty("Cookie", "cer=appstore.com.qingfengxulai; feedback=1; isShowTask=1; ranking_tip=2018-10-23; sessionid=\"" + sessionid + "\"; CERT_COOKIE=appstore.qingfengxulai; CERT_TOKEN=" + CERT_TOKEN + "; Hm_lpvt_73586d9456385c1f6e1b51968afe865d=" + String.valueOf(datetime) + "; Hm_lvt_73586d9456385c1f6e1b51968afe865d=" + dates + "; cer=appstore.; zb1023=1");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("DNT", "1");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			
			httpURLConnection.connect();
			
			//处理内容
            GZIPInputStream gzipInputStream = new GZIPInputStream(httpURLConnection.getInputStream());
            byte[] b = new byte[1024];
            int i;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            while((i = gzipInputStream.read(b)) != -1) {
            	out.write(b, 0, i);
            }
            analysisResult(out.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	// 解析结果DOM操作
	public Map<String, Object> analysisResult(String html) {
		System.out.println(html);
		Map<String, Object> map = new HashMap<>();
		Document document = Jsoup.parse(html);
		Elements els = document.getElementsByClass("ongoing");
		if (els.size() > 0) {
			Element ongoing = els.get(0);// 获取又正在进行中的节点
			if (ongoing.hasText()) {
				Task task = new Task();
				task.setImg(ongoing.getElementsByTag("img").get(0).attr("src"));
				Element el = ongoing.getElementsByClass("app-name").get(0);
				Element reward = ongoing.getElementsByClass("reward").get(0);
				task.setBundleid(el.attr("bundleid"));
				task.setReward(reward.text());

				map.put("status", true);// 有正在进行的任务
				map.put("task", task);
				return map;
			}
		}
		return null;
	}

}
