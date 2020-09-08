package com.lovelive.common.uitls;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列 utils
 */
@Component
public class DelayedUtils {

    private static final Logger log = LoggerFactory.getLogger(DelayedUtils.class);

    /**
     * 延时队列
     */
    private static final DelayQueue<MyDelayed> queue = new DelayQueue<>();

    /**
     * 延迟时间
     */
    private static final long delayTime = 30 * 60 * 1000;

    synchronized public static void add() {
        //TODO
        MyDelayed delayed = new MyDelayed("1", delayTime);
        DelayedUtils.queue.offer(delayed);

        log.info("add delayed[]");
    }

    synchronized public static void remove() {
        //TODO
        MyDelayed delayed = new MyDelayed("1", delayTime);
        DelayedUtils.queue.remove(delayed);

        log.info("remove delayed[]");
    }

    @Autowired
    public void init(ThreadPoolTaskExecutor executor) {
        log.info("init ... ");

        // 启动消费线程
        executor.execute(new Consumer(queue));

        if (BooleanUtils.toBoolean(System.getProperty("executeDelayedInit"))) {
            try {
                // TODO 初始化队列
            } catch (Exception e) {
                log.error("初始化队列失败", e);
            }
        }

    }
}

/**
 * 消费者
 */
class Consumer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    // 延时队列，消费者从其中获取消息进行消费
    private final DelayQueue<MyDelayed> queue;

    public Consumer(DelayQueue<MyDelayed> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (Thread.interrupted()) {
                    log.info("interrupted ... ");
                    break;
                }

                MyDelayed take = queue.take();

                log.info("run delayed[" + take.getPayRecordId() + "]");

                //TODO  执行任务

            } catch (InterruptedException e) {
                log.error("", e);
                break;
            }
        }
    }
}

/**
 * 延迟队列
 */
class MyDelayed implements Delayed {

    private String payRecordId;

    private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。

    public String getPayRecordId() {
        return payRecordId;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public MyDelayed(String payRecordId, long delayTime) {
        this.payRecordId = payRecordId;
        this.excuteTime = System.currentTimeMillis() + delayTime;
    }

    public MyDelayed(String payRecordId, Date endTime) {
        this.payRecordId = payRecordId;
        this.excuteTime = endTime.getTime();
    }

    // 自定义实现比较方法返回 1 0 -1三个参数
    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.excuteTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDelayed that = (MyDelayed) o;
        return Objects.equals(payRecordId, that.payRecordId);
    }

}