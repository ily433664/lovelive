package com.lovelive.common.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * @author dhe
 * @ClassName: HttpRequestUtil
 * @Description: 发送请求返回json数据
 * @date 2018年1月22日 上午9:08:58
 */
public class HttpRequestUtil {

    /**
     * 发起http请求并获取结果,GET
     *
     * @param requestUrl 请求地址
     * @param headerMap 添加头部信息
     */
    public static JsonObject sendGetRequest(String requestUrl, Map<String, String> headerMap) {
        String res;
        JsonObject object = null;
        StringBuilder buffer = new StringBuilder();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(10000);//连接超时 单位毫秒
            connection.setReadTimeout(10000);//读取超时 单位毫秒
            //设置请求头
            if (headerMap != null) {
                headerMap.forEach(connection::setRequestProperty);
            }
            if (200 == connection.getResponseCode()) {
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is, "utf-8");
                BufferedReader br = new BufferedReader(isr);

                String str;
                while ((str = br.readLine()) != null) {
                    buffer.append(str);
                }
                br.close();
                isr.close();
                is.close();
                res = buffer.toString();
                JsonParser parse = new JsonParser();
                object = (JsonObject) parse.parse(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 发起http请求并获取结果,POST
     *
     * @param requestUrl 请求地址
     * @param parameter  请求参数，xx=xx&yy=yy
     * @param headerMap 添加头部信息
     */
    public static JsonObject sendPostRequest(String requestUrl, String parameter, Map<String, String> headerMap) {
        URL url;
        try {
            url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");// 提交模式
            connection.setConnectTimeout(10000);//连接超时 单位毫秒
            connection.setReadTimeout(10000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //设置请求头
            if (headerMap != null) {
                headerMap.forEach(connection::setRequestProperty);
            }
            // 获取URLConnection对象对应的输出流
            PrintWriter printWriter = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            printWriter.write(parameter);// post的参数 xx=xx&yy=yy
            // flush输出流的缓冲
            printWriter.flush();
            // 开始获取数据
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
                bos.flush();
            }
            bos.close();
            JsonParser parse = new JsonParser();
            return (JsonObject) parse.parse(bos.toString("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
