package com.bawei.mvp.net;

import android.os.Handler;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/*
 *@Auther:甄帅
 *@Date: 时间
 *@Description:功能
 * */public class HttpUtils {
    private HttpUtils() {
    }
    //单例
    private static HttpUtils utils=null;
    public synchronized static HttpUtils utils(){
        if (utils==null){
            utils=new HttpUtils();
        }
        return utils;
    }
    private Handler handler;
    //数据请求
    public String doHttpUtils(final String url, final Map<String,String>param, final NetCallback callback){
           new Thread(){
               @Override
               public void run() {
                   //请求网络
                   HttpURLConnection connection=null;
                   try {
                       URL url1=new URL(url);
                       connection= (HttpURLConnection) url1.openConnection();
                       connection.setRequestMethod("POST");
                       connection.setConnectTimeout(5000);
                       connection.setReadTimeout(5000);
                       //向服务器写入数据
                       String body = paramToString(param);
                       connection.setDoOutput(true);
                       connection.getOutputStream().write(body.getBytes());
                       if (connection.getResponseCode()==200){
                           InputStream stream=connection.getInputStream();
                           BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
                           final StringBuffer buffer=new StringBuffer();
                           String str="";
                           while ((str=reader.readLine())!=null){
                               buffer.append(str);
                           }
                           stream.close();
                           connection.disconnect();
                           //返回到主线程
                          handler.post(new Runnable() {
                              @Override
                              public void run() {
                                callback.onSuccess(buffer.toString());
                              }
                          });
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
                   super.run();
               }
           }.start();
           return null;
    }
    /**
     * 把param 转成成 字符串
     * @param param
     * @return
     */
    private String paramToString(Map<String,String>param) {
        StringBuilder stringBuilder=new StringBuilder();
        //变量map
        for (Map.Entry<String,String>entry : param.entrySet()) {
           //把Map Entry的键和值封装成参数
            stringBuilder.append(entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(entry.getValue()));
            stringBuilder.append("&");
        }
        return stringBuilder.toString().substring(0,stringBuilder.toString().length()-1);
    }
}
