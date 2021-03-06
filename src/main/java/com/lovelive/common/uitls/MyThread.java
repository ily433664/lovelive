package com.lovelive.common.uitls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程
 *
 * @author dHe
 */
public abstract class MyThread implements Runnable {

    protected static final Logger log = LoggerFactory.getLogger(MyThread.class);

    protected static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public MyThread() {
    }

    public abstract void run();

    /**
     * 执行线程
     */
    public static void runThread(MyThread thread) {
        try {
            Thread th = new Thread(thread);
            threadPool.execute(th);
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
