package com.puzhibing.trialtask.util;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 短信处理类
 */
@Component
public class SMSUtil {
    // 短信应用SDK AppID
    private int appid = 1400151656; // 1400开头

    // 短信应用SDK AppKey
    private String appkey = "392c12c8b6b103f99eae9cffe2973695";

    // 需要发送短信的手机号码
    private String[] phoneNumbers = {"15828353127"};

    // 短信模板ID，需要在短信应用中申请
    private int templateId = 213643; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
    //templateId7839对应的内容是"您的验证码是: {1}"
    // 签名
    private String smsSign = "TrialTask"; // NOTE: 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`


    /**
     * 发送短信处理
     * @param platform
     */
    public void sendSMS(String platform){
        try {
            String[] params = {platform};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                    templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
    }

}
