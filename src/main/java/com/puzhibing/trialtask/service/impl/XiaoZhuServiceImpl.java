package com.puzhibing.trialtask.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.puzhibing.trialtask.pojo.xiaozhu.Result;
import com.puzhibing.trialtask.pojo.xiaozhu.Task;
import com.puzhibing.trialtask.service.XiaoZhuService;
import com.puzhibing.trialtask.util.ComUtil;
import com.puzhibing.trialtask.util.ResultUtil;
import com.puzhibing.trialtask.util.SMSUtil;

@Service
public class XiaoZhuServiceImpl implements XiaoZhuService {
	
	@Autowired
    private SMSUtil smsUtil;
	
	@Autowired
    private ComUtil comUtil;

	@Override
	public ResultUtil getTaskList() {
		ResultUtil resultUtil = new ResultUtil();
		boolean bl = false;
		Long datetime = System.currentTimeMillis()/1000;
		String dates = String.valueOf(datetime - 15000) + "," + String.valueOf(datetime - 9200) + "," + String.valueOf(datetime - 5400) + "," + String.valueOf(datetime);
		String path = "http://integral.xckoo.com/tasklist";
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.addRequestProperty("Host", "integral.xckoo.com");
			httpURLConnection.addRequestProperty("Referer", "http://integral.xckoo.com/index");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpURLConnection.addRequestProperty("Accept-Language", "zh-cn");
			httpURLConnection.addRequestProperty("Cookie", "Hm_lvt_76d21571c9143a772d8e2f6cd4a0d38b=" + dates + "; sessionid=13u85o5t495xgefvncrg1smrpcardn7o");
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
            
            Map<String, Object> map = analysisResult(out.toString());
            if((boolean)map.get("status")) {//判断是否有任务正在进行中
            	Task task = (Task)map.get("task");
            	resultUtil.setMsg("xiaozhu");
        		resultUtil.setStatus(true);
        		resultUtil.setImg(task.getImg());
        		resultUtil.setInfo(task.getBundleid());
        		System.err.println("小猪成功");
        		return resultUtil;
            }
            
            Task task = (Task)map.get("task");
            if(null != task) {
            	bl = grabTask(task.getMissionid());
            	if(bl) {
            		resultUtil.setMsg("xiaozhu");
            		resultUtil.setStatus(true);
            		resultUtil.setImg(task.getImg());
            		resultUtil.setInfo(task.getBundleid());
            		System.err.println("小猪成功");
            		System.err.println("发短信！");
                    smsUtil.sendSMS("小猪APP");//调用发送短信处理
            		return resultUtil;
            	}else {
            		resultUtil.setMsg("领取失败");
            		resultUtil.setStatus(false);
            	}
            }else {
            	resultUtil.setMsg("任务为空");
        		resultUtil.setStatus(false);
            }
            
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resultUtil;
	}
	
	
	
	//解析结果DOM操作
	public Map<String, Object> analysisResult(String html) {
		Map<String, Object> map = new HashMap<>();
		Document document = Jsoup.parse(html);
		Element ongoing = document.getElementsByClass("ongoing").get(0);//获取又正在进行中的节点
		if(ongoing.hasText()) {
			Task task = new Task();
			task.setImg(ongoing.getElementsByTag("img").get(0).attr("src"));
			Element el = ongoing.getElementsByClass("app-name").get(0);
			Element reward = ongoing.getElementsByClass("reward").get(0);
			task.setBundleid(el.attr("bundleid"));
			task.setReward(reward.text());
			
			map.put("status", true);//有正在进行的任务
			map.put("task", task);
			return map;
		}
		
		//没有则遍历任务列
		Element element = document.getElementById("tasks");//获取标准任务节点
		Elements elements = element.getElementsByClass("task-action");//获取标准任务节点下的class值为task-action的节点
		List<Task> tasks = new ArrayList<>();
		for (Element e : elements) {
			Task task = new Task();
			task.setImg(e.getElementsByTag("img").get(0).attr("src"));
			Element el = e.getElementsByClass("app-name").get(0);
			Element tag = e.getElementsByClass("tag").get(0);
			Element reward = e.getElementsByClass("reward").get(0);
			task.setUid(el.attr("uid"));
			task.setMissionid(el.attr("missionid"));
			task.setBundleid(el.attr("bundleid"));
			task.setAppid(el.attr("appid"));

			String regEx="[^0-9]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(tag.text());
			System.err.println("数量：" + m.replaceAll("").trim());
			task.setTag(m.replaceAll("").trim());
			task.setReward(reward.text());
			
			tasks.add(task);
		}
		
		//循环遍历出金额最大且数量大于0的任务
		Task task = null;
		float am = 0f;
		for (Task t : tasks) {
			//判断当前任务是否在放弃任务集合中，包含则不需要抢该任务
			boolean b = comUtil.judgeWhetherIncludeValue("xiaozhu", t.getBundleid());
			if(b) {
				continue;
			}

			System.out.println(t.toString());
			float f = Float.parseFloat(t.getReward());
			if(f >= am && Integer.valueOf(t.getTag()) > 0) {
				task = t;
				am = f;
			}
		}
		map.put("status", false);
		map.put("task", task);
		return map;
	}
	
	
	
	//抢任务
	public boolean grabTask(String missionid) {
		boolean bl = false;
		Long datetime = System.currentTimeMillis()/1000;
		String dates = String.valueOf(datetime - 15000) + "," + String.valueOf(datetime - 9200) + "," + String.valueOf(datetime - 5400) + "," + String.valueOf(datetime);
		String path = "http://integral.xckoo.com/recvtask?auth=Q7NwsHzevdO%2BDJV%2BQPV7Jgv%2FX%2BSI8lUui5We%2FlYa9KGTgfpAivRsaoo55ktHKqDHN9B2HmigXRP6cY3XXpJAHYXNnivlzjJQCH1iYvBYtDlvv%2BR4SA5VffLC%2Fhzj7BnwjbLrZ4dBYkUTCOXT3eO1P6JMZMxgy%2BUug2tZM8TQYuOtQ3TFv%2FnvTeEessE49bpJqQWwlICVqMFt%2B%2FpWJNZupFzZFXlXhP%2FvqbjcmVf8cT2EIMpLtUzculhlvggRNaqu%2Bnx4nskE5zDekq7HDnEXaKXN3YsPP9qV1dJIl4UOag6gdKdIgKIEAXtU4SqZ%2FbEz6eJ0omI%2BNuaKy6DcaxHOepr4snPQ4vhMrxdjExT%2Ba4ThE1LOZJdGnQBQ8WJx7jbP2keCt9efiHAxGuUY5xqfel7HeOdW4tt3%2B1lw7GRO2PA3jYITXwUKo8wWHpnnOz%2BzkkZgyHwbkATSvzGG%2F%2FRKgmrRscFLXpwLt2dpxn0HFoyNOFwEc4ZXY7uRhCeI5yWTUfuLpizFVF5kwk%2B7%2BORW78lNVQWykKQqOPjddTW2Qp%2BlcIS%2Fr26OeTa3nB1gjtByq3rxB%2BBcnyJpoLXUzwNL4A%3D%3D&missionid=" + missionid + "&time=" + String.valueOf(System.currentTimeMillis() / 1000);
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.addRequestProperty("Host", "integral.xckoo.com");
			httpURLConnection.addRequestProperty("Referer", "http://integral.xckoo.com/tasklist");
			httpURLConnection.addRequestProperty("X-Requested-With", "XMLHttpRequest");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
			httpURLConnection.addRequestProperty("Accept-Encoding", "gzip, deflate");
			httpURLConnection.addRequestProperty("Accept-Language", "zh-cn");
			
			httpURLConnection.addRequestProperty("Cookie", "Hm_lvt_76d21571c9143a772d8e2f6cd4a0d38b=" + dates + "; sessionid=13u85o5t495xgefvncrg1smrpcardn7o");
			httpURLConnection.addRequestProperty("Connection", "keep-alive");
			httpURLConnection.addRequestProperty("DNT", "1");
			httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_3_5 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13G36 Safari/601.1");
			
			httpURLConnection.connect();
			
            
            //处理内容
            // 定义 BufferedReader输入流来读取URL的响应
            String result = "";
            BufferedReader in = null;
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream() , "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.err.println(result);
            Result res = JSON.parseObject(result, Result.class);
            if(res.getRet() >= 0) {
            	bl = true;
            }
            
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bl;
	}
}