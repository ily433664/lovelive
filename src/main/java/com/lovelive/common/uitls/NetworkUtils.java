package com.lovelive.common.uitls;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * 网络工具类
 *
 * @author dHe
 * @date 2019-12-19
 */
@Component
public class NetworkUtils {

    /**
     * 获取request网络ip
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                if (inet != null) {
                    ipAddress = inet.getHostAddress();
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        /*if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()= 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }*/
        return ipAddress;
    }

    /**
     * 发起http请求并获取结果,GET
     *
     * @param requestUrl 请求地址
     * @param headerMap  添加头部信息
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
     * @param headerMap  添加头部信息
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

    /**
     * 判断是否是移动端
     *
     * @param userAgent
     * @return
     */
    public static boolean isMobile(String userAgent) {
        if (userAgent != null) {
            String[] agents = new String[]{"android", "iphone", "symbianos", "windows phone", "ipad", "ipod"};
            for (String agent : agents) {
                if (userAgent.toLowerCase().contains(agent)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断字符串是否为URL
     *
     * @param urls 需要判断的String类型url
     * @return true:是URL；false:不是URL
     */
    public static boolean isHttpUrl(String urls) {
        try {
            URL url = new URL(urls);
            if (url.getHost() != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

}
