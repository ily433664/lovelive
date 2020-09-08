package com.lovelive.modules.sys.web;

import com.lovelive.common.base.BaseController;
import com.lovelive.modules.sys.entity.OperationLog;
import com.lovelive.modules.sys.service.IOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志 controller
 *
 * GET（SELECT）：从服务器取出资源（一项或多项）。
 * POST（CREATE）：在服务器新建一个资源。
 * PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
 * PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
 * DELETE（DELETE）：从服务器删除资源。
 *
 * GET /zoos：列出所有动物园
 * POST /zoos：新建一个动物园
 * GET /zoos/ID：获取某个指定动物园的信息
 * PUT /zoos/ID：更新某个指定动物园的信息（提供该动物园的全部信息）
 * PATCH /zoos/ID：更新某个指定动物园的信息（提供该动物园的部分信息）
 * DELETE /zoos/ID：删除某个动物园
 * GET /zoos/ID/animals：列出某个指定动物园的所有动物
 * DELETE /zoos/ID/animals/ID：删除某个指定动物园的指定动物
 *
 * @author dhe
 */
@RestController
@RequestMapping(value = "/operationLog")
public class OperationLogController extends BaseController {

    private final IOperationLogService operationLogService;

    @Autowired
    public OperationLogController(IOperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getPage(OperationLog operationLog) {
        try {
            Page<OperationLog> page = operationLogService.find(operationLog);
            return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getOperationLog(@PathVariable("id") Long id) {
        try {
            OperationLog operationLog = operationLogService.getOperationLog(id);
            if (operationLog != null) {
                return new ResponseEntity<>(operationLog, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post() {
        try {
            OperationLog operationLog = new OperationLog();
            operationLogService.saveOperationLog(operationLog);
            return new ResponseEntity<>(operationLog, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> put(@PathVariable("id") Long id) {
        try {
            OperationLog operationLog = operationLogService.getOperationLog(id);
            if (operationLog != null) {
                operationLogService.saveOperationLog(operationLog);
                return new ResponseEntity<>(operationLog, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        try {
            OperationLog operationLog = operationLogService.getOperationLog(id);
            if (operationLog != null) {
                operationLogService.deleteOperationLog(operationLog);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
