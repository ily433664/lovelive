package com.lovelive.common.uitls;

import org.apache.commons.codec.Charsets;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * http client 工具类
 *
 * @author dHe
 */
public class HttpClientUtils {

    private static HttpClient httpClient;

    /**
     * 最大连接数
     */
    private static final int MAX_CONNECTION = 100;
    /**
     * 每个route能使用的最大连接数，一般和MAX_CONNECTION取值一样
     */
    private static final int MAX_CONCURRENT_CONNECTIONS = 100;
    /**
     * 建立连接的超时时间，单位毫秒
     */
    private static final int CONNECTION_TIME_OUT = 10000;
    /**
     * 请求超时时间，单位毫秒
     */
    private static final int REQUEST_TIME_OUT = 10000;
    /**
     * 最大失败重试次数
     */
    private static final int MAX_FAIL_RETRY_COUNT = 3;
    /**
     * 请求配置，可以复用
     */
    private static RequestConfig requestConfig;

    static {
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoTimeout(REQUEST_TIME_OUT)
                .setSoKeepAlive(true)
                .setTcpNoDelay(true).build();

        requestConfig = RequestConfig.custom()
                .setSocketTimeout(REQUEST_TIME_OUT)
                .setConnectTimeout(CONNECTION_TIME_OUT).build();

        // 每个默认的 ClientConnectionPoolManager 实现将给每个route创建不超过2个并发连接，最多20个连接总数。
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(MAX_CONNECTION);
        connManager.setDefaultMaxPerRoute(MAX_CONCURRENT_CONNECTIONS);
        connManager.setDefaultSocketConfig(socketConfig);

        httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setRetryHandler(new MyHttpRequestRetryHandler()).build();  // 添加重试处理器
    }

    public static void main(String[] args) {
        try {
            testPost();
            testGet();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试get方法
     */
    private static void testGet() {
        String url = "http://xiaoyoudemo.dcampus.com/alumni//interface/getAlumniInfo.jsp";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("currentTimeMillis", System.currentTimeMillis() + "");
        paramMap.put("account", "445281199604213135");

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", "token"));

        try {
            String result = get(url, paramMap, headers);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试post方法
     */
    private static void testPost() {
        String url = "http://xiaoyoudemo.dcampus.com/alumni//interface/editAlumniInfo.jsp";

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("currentTimeMillis", System.currentTimeMillis() + "");
        paramMap.put("account", "445281199604213135");
        paramMap.put("sex", "2");
        paramMap.put("birthDate", "1996-04-21");
        paramMap.put("nativePlace", "广东广州");
        paramMap.put("nationCode", "1");
        paramMap.put("workUnit", "%E5%B9%BF%E5%B7%9E%E7%BE%8E%E6%9C%AF%E5%AD%A6%guang%99%A2");
        paramMap.put("job", "%E6%97%A0");
        paramMap.put("clanCode", "1");
        paramMap.put("mobilePhone1", "13512345678");
        paramMap.put("email", "710122069@qq.com");
        paramMap.put("addressArea", "2");
        paramMap.put("address", "%E6%97%A0");
        paramMap.put("schoolRecordId", "131");
        paramMap.put("graduateYear_131", "2015");
        paramMap.put("diplomaId_131", "45");
        paramMap.put("departmentId_131", "114");
        paramMap.put("specialtyId_131", "1753");
        paramMap.put("studentNo_131", "");

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", "token"));

        try {
            String result = post(url, paramMap, headers);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * post请求
     *
     * @param url
     * @param paramMap
     * @param headers
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> paramMap, List<Header> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> content = new ArrayList<>();
        if (paramMap != null) {
            // 添加请求参数
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                if (entry.getValue().getClass().isArray()) {
                    for (Object obj : (List) entry.getValue()) {
                        if (obj != null) {
                            content.add(new BasicNameValuePair(entry.getKey(), String.valueOf(obj)));
                        }
                    }
                } else {
                    if (entry.getValue() != null) {
                        content.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
                    }
                }
            }
        }
        if (content.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, "UTF-8");
            httpPost.setEntity(entity);
        }

        if (headers != null) {
            // 添加请求首部
            for (Header header : headers) {
                httpPost.addHeader(header);
            }
        }

        httpPost.setConfig(requestConfig);

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);

        return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
    }

    /**
     * post请求，不带请求首部
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> paramMap) throws IOException {
        return post(url, paramMap, null);
    }

    /**
     * jwt post请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws IOException
     */
    public static String postByJwt(String url, Map<String, Object> paramMap, String token) throws IOException {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", token));
        return post(url, paramMap, headers);
    }

    /**
     * get请求
     *
     * @param url
     * @param paramMap
     * @param headers
     * @return
     * @throws URISyntaxException, IOException
     */
    public static String get(String url, Map<String, Object> paramMap, List<Header> headers) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (paramMap != null) {
            // 添加请求参数
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                if (entry.getValue().getClass().isArray()) {
                    for (Object obj : (List) entry.getValue()) {
                        if (obj != null) {
                            uriBuilder.addParameter(entry.getKey(), String.valueOf(obj));
                        }
                    }
                } else {
                    if (entry.getValue() != null) {
                        uriBuilder.addParameter(entry.getKey(), String.valueOf(entry.getValue()));
                    }
                }
            }
        }

        HttpGet httpGet = new HttpGet(uriBuilder.build());
        if (headers != null) {
            // 添加请求首部
            for (Header header : headers) {
                httpGet.addHeader(header);
            }
        }

        httpGet.setConfig(requestConfig);

        HttpResponse response = httpClient.execute(httpGet);// 执行请求

        return EntityUtils.toString(response.getEntity(), Charsets.UTF_8);
    }

    /**
     * get请求，不带请求首部
     *
     * @param url
     * @param paramMap
     * @return
     * @throws URISyntaxException, IOException
     */
    public static String get(String url, Map<String, Object> paramMap) throws URISyntaxException, IOException {
        return get(url, paramMap, null);
    }

    /**
     * jwt get请求
     *
     * @param url
     * @param paramMap
     * @return
     * @throws URISyntaxException, IOException
     */
    public static String getByJwt(String url, Map<String, Object> paramMap, String token) throws URISyntaxException, IOException {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", token));
        return get(url, paramMap, headers);
    }

    /**
     * 请求重试处理器
     */
    private static class MyHttpRequestRetryHandler implements HttpRequestRetryHandler {
        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if (executionCount >= MAX_FAIL_RETRY_COUNT) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                // 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {
                // 未知主机
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                // 连接被拒绝
                return false;
            }
            if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            }

            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // 如果请求被认为是幂等的，则重试
                return true;
            }

            return false;
        }
    }

}
