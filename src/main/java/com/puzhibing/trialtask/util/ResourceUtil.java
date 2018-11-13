package com.puzhibing.trialtask.util;

import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.*;


/**
 * 读取静态资源
 */
public class ResourceUtil {

    private static ResourceUtil resourceUtil;

    private String fileName;

    private ResourceUtil(){}

    /**
     * 获取对象
     * @param fileName
     * @return
     */
    public static ResourceUtil getResourceUtil(String fileName){
        resourceUtil = new ResourceUtil();
        resourceUtil.setFileName(fileName);
        return resourceUtil;
    }


    /**
     * 获取文件内容
     * @return
     */
    private String getFileContent(){
        File file = null;
        BufferedReader bufferedReader = null;
        FileReader fileReader = null;
        StringBuffer stringBuffer = null;
        try {
            file = ResourceUtils.getFile("classpath:" + this.fileName);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str = null;
            stringBuffer = new StringBuffer();
            // 一次读入一行，直到读入null为文件结束
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str).append("&");
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }


    /**
     * 获取属性值
     * @param key
     * @return
     */
    public String getValue(String key){
        String str = key.trim();
        String v = "";
        if(!StringUtils.isEmpty(str)){
            String[] strings = this.getFileContent().trim().split("&");
            for (int i = 0; i < strings.length; i++){
                String[] values = strings[i].split("=");
                String k = values[0].trim();
                if(str.equals(k)){
                    if(values.length > 1){
                        v = values[1].trim();
                    }
                }
            }
        }
        return v;
    }


    /**
     * 给属性添加值
     * @param key
     * @param value
     */
    public void setValue(String key , String value) throws Exception {
        String k = key.trim();
        String v = value.trim();
        boolean b = false;
        StringBuffer stringBuffer = new StringBuffer();
        if(!(StringUtils.isEmpty(k) && StringUtils.isEmpty(v))){
            String[] results = this.getFileContent().split("&");
            for (int i = 0; i < results.length; i++){
                String[] rs = results[i].split("=");
                if(rs[0].trim().equals(k)){
                    String s = "";
                    if(rs.length > 1){
                        s = rs[1];
                    }
                    String val = this.replaceString(s , v);
                    stringBuffer.append(rs[0]).append("=").append(val).append("&");
                    b = true;
                    continue;
                }
                stringBuffer.append(results[i]).append("&");
            }

            if(b){
                this.writeData(stringBuffer.toString());
            }else{
                throw new Exception(key + "：在文件中未定义");
            }


        }
    }


    /**
     * 定义替换字符串中字符（保留字符串前后空格）
     * @param str
     * @return
     */
    public String replaceString(String str , String result){
        char[] chars = str.toCharArray();
        int head = 0;
        int end = 0;
        for (int i = 0 ; i < chars.length ; i++){
            if(chars[i] == ' '){
                head++;
                continue;
            }
            break;
        }

        for (int i = chars.length - 1 ; i >= 0 ; i--){
            if(chars[i] == ' '){
                end++;
                continue;
            }
            break;
        }

        StringBuffer stringBuffer = new StringBuffer(result.trim());
        while (head > 0){
            stringBuffer.insert(0 , " ");
            head--;
        }

        while (end > 0){
            stringBuffer.append(" ");
            end--;
        }
        return stringBuffer.toString();
    }


    /**
     * 更新文件中的内容
     * @param str
     */
    public void writeData(String str){
        File file = null;
        BufferedWriter bufferedWriter = null;
        try {
            file = ResourceUtils.getFile("classpath:" + this.fileName);
            file.setWritable(true , true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));

            String[] strings = str.trim().split("&");
            for (int i = 0 ; i < strings.length ; i++){
                bufferedWriter.write(strings[i]);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }



    private void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
