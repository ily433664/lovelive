package com.lovelive.sys.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 短信util
 */
public class MobileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileUtils.class);

    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    /**
     * 发送短信的具体操作
     *
     * @param to   接受人
     * @param text 发送内容
     */
    public static String sendMobile(String to, String text) {
        StringBuilder result = new StringBuilder();

        try {
            //TODO
            new RuntimeException("the method is not implement");

        } catch (Exception e) {
            LOGGER.error("", e);
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    /**
     * 单线程发送短信
     *
     * @param tos  收件人
     * @param text 内容
     */
    public static String runSendMobile(String[] tos, String text) {
        StringBuilder result = new StringBuilder();
        for (String to : tos) {
            result.append(MobileUtils.sendMobile(to, text));
        }
        return result.toString();
    }

    /**
     * 启动多线程发送短信
     * 如果是直接使用，则调用该方法
     *
     * @param tos  收件人
     * @param text 内容
     */
    public static void runSendMobileThread(String[] tos, String text) {
        try {
            for (String to : tos) {
                MobileThread mailThread = new MobileThread(to, text);
                Thread th = new Thread(mailThread);
                threadPool.execute(th);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }

    /**
     * 发送短信线程
     */
    public static class MobileThread implements Runnable {

        //收件人
        private String to;
        //内容
        private String text;

        private MobileThread() {
        }

        public MobileThread(String to, String text) {
            this.to = to;
            this.text = text;
        }

        public void run() {
            MobileUtils.sendMobile(to, text);
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static void main(String[] args) {
        String to = "dhe@dcampus.com";
        String text = "测试短信内容";
        try {
            MobileUtils.sendMobile(to, text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
