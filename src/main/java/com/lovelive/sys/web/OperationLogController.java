package com.lovelive.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/operationLog")
public class OperationLogController extends BaseController {

    private IOperationLogService operationLogService;


    @Autowired
    public OperationLogController(IOperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationLogById(@PathVariable("id") String id) {
        try {
            OperationLog operationLog = operationLogService.getOperationLog(id);
            if (operationLog != null) {
                return new ResponseEntity(operationLog, HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list(OperationLog operationLog) {
        try {
            /*operationLog.setOperAccount("aaa");
            operationLog.setOperIP("127.0.0.1");*/
            Page<OperationLog> page = operationLogService.find(operationLog);
            return new ResponseEntity(page.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
