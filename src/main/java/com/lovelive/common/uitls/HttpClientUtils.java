package com.lovelive.common.uitls;

import com.fasterxml.jackson.databind.JsonNode;
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

    private static final HttpClient httpClient;
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
    private static final RequestConfig requestConfig;
    /**
     * 默认编码格式
     */
    private static final String charset = "UTF-8";

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
        String url = "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("currentTimeMillis", System.currentTimeMillis());

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", "token"));

        try {
            String result = get(url, paramMap, headers, charset);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试post方法
     */
    private static void testPost() {
        String url = "";

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("currentTimeMillis", System.currentTimeMillis());

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", "token"));

        try {
            String result = post(url, paramMap, headers, charset);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * post请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param headers  请求头
     * @param charset  编码格式
     * @return String
     * @throws IOException
     */
    private static String post(String url, Map<String, Object> paramMap, List<Header> headers, String charset) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        List<NameValuePair> content = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            // 添加请求参数
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                if (entry == null) {
                    continue;
                }
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
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(content, charset);
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

        return EntityUtils.toString(response.getEntity(), charset);
    }

    /**
     * post请求，不带请求首部
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @return String
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> paramMap) throws IOException {
        return post(url, paramMap, charset);
    }

    /**
     * post请求，不带请求首部
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param charset  编码格式
     * @return String
     * @throws IOException
     */
    public static String post(String url, Map<String, Object> paramMap, String charset) throws IOException {
        return post(url, paramMap, null, charset);
    }

    /**
     * jwt post请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @return JsonNode
     * @throws IOException
     */
    public static String postByJwt(String url, Map<String, Object> paramMap, String token) throws IOException {
        return postByJwt(url, paramMap, token, charset);
    }

    /**
     * jwt post请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @param charset  编码格式
     * @return JsonNode
     * @throws IOException
     */
    public static String postByJwt(String url, Map<String, Object> paramMap, String token, String charset) throws IOException {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", token));
        return post(url, paramMap, headers, charset);
    }

    /**
     * jwt post请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @return JsonNode
     * @throws IOException
     */
    public static JsonNode postByJwtToJsonNode(String url, Map<String, Object> paramMap, String token) throws IOException {
        return postByJwtToJsonNode(url, paramMap, token, charset);
    }

    /**
     * jwt post请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @param charset  编码格式
     * @return JsonNode
     * @throws IOException
     */
    public static JsonNode postByJwtToJsonNode(String url, Map<String, Object> paramMap, String token, String charset) throws IOException {
        return JsonUtils.toJsonNode(postByJwt(url, paramMap, token, charset));
    }

    /**
     * get请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param headers  请求头
     * @param charset  编码格式
     * @return String
     * @throws URISyntaxException
     * @throws IOException
     */
    private static String get(String url, Map<String, Object> paramMap, List<Header> headers, String charset) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        if (paramMap != null) {
            // 添加请求参数
            for (Entry<String, Object> entry : paramMap.entrySet()) {
                if (entry == null) {
                    continue;
                }
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

        return EntityUtils.toString(response.getEntity(), charset);
    }

    /**
     * get请求，不带请求首部
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @return String
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String get(String url, Map<String, Object> paramMap) throws URISyntaxException, IOException {
        return get(url, paramMap, charset);
    }

    /**
     * get请求，不带请求首部
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param charset  编码格式
     * @return String
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String get(String url, Map<String, Object> paramMap, String charset) throws URISyntaxException, IOException {
        return get(url, paramMap, null, charset);
    }

    /**
     * jwt get请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @return JsonNode
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String getByJwt(String url, Map<String, Object> paramMap, String token) throws URISyntaxException, IOException {
        return getByJwt(url, paramMap, token, charset);
    }

    /**
     * jwt get请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @param charset  编码格式
     * @return JsonNode
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String getByJwt(String url, Map<String, Object> paramMap, String token, String charset) throws URISyntaxException, IOException {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("X-AUTH-TOKEN", token));
        return get(url, paramMap, headers, charset);
    }

    /**
     * jwt get请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @return JsonNode
     * @throws URISyntaxException
     * @throws IOException
     */
    public static JsonNode getByJwtToJsonNode(String url, Map<String, Object> paramMap, String token) throws URISyntaxException, IOException {
        return getByJwtToJsonNode(url, paramMap, token, charset);
    }

    /**
     * jwt get请求
     *
     * @param url      请求地址
     * @param paramMap 参数
     * @param token    令牌
     * @param charset  编码格式
     * @return JsonNode
     * @throws URISyntaxException
     * @throws IOException
     */
    public static JsonNode getByJwtToJsonNode(String url, Map<String, Object> paramMap, String token, String charset) throws URISyntaxException, IOException {
        return JsonUtils.toJsonNode(getByJwt(url, paramMap, token, charset));
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
            if (exception instanceof ConnectTimeoutException) {
                // 连接被拒绝
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
