package com.lovelive.sys.task;

import com.lovelive.sys.service.IOperationLogService;
import com.lovelive.sys.utils.MyThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 定时任务
 *
 * @author dHe
 * @date 2019-12-24
 */
@Component
public class TimerTask {

    private static final Logger log = LoggerFactory.getLogger(TimerTask.class);

    private IOperationLogService operationLogService;

    @Autowired
    public TimerTask(IOperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @InitBinder
    public void init() {
        log.info("TimerTask init ... ");
    }

    /**
     * 删除超过100天的日志
     */
    @Scheduled(cron = "0 0 2 * * ?")    //每天凌晨两点执行
    public void DeleteOperationLogTaskCycle() {

        try {
            //TODO
            log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " start ... ");

            DeleteOperationLogThread thread = new DeleteOperationLogThread();
            MyThread.runThread(thread);

            log.info(Thread.currentThread().getStackTrace()[1].getMethodName() + " end ... ");
        } catch (Exception e) {
            log.error("", e);
        }

    }

    private void deleteOperationLog() {
        operationLogService.getOperationLog("1");
    }

    /**
     * 线程
     */
    public class DeleteOperationLogThread extends MyThread {

        public DeleteOperationLogThread() {
            super();
        }

        public void run() {
            //执行方法
            deleteOperationLog();
        }

    }

}
