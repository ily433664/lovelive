package com.lovelive.sys.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lovelive.sys.entity.OperationLog;
import com.lovelive.sys.service.ILogService;
import com.lovelive.sys.base.BaseController;
import com.lovelive.sys.anno.LogAnnotation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping(value = "/sys")
public class LogController extends BaseController {

    private ILogService logService;

    @Autowired
    public LogController(ILogService logService) {
        this.logService = logService;
    }

    @LogAnnotation(mold = OperationLog.OPER_TYPE_QUERY, methods = "查询操作日志")
    @RequiresRoles(value = {"root", "manage"}, logical = Logical.OR)
    @RequestMapping("logList")
    public String operLogList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        PageHelper.startPage(pageNo, pageSize);
        List<OperationLog> list = logService.getOperationLog();
        PageInfo<OperationLog> pageInfo = new PageInfo<>(list);

        model.addAttribute("pageInfo", pageInfo);
        return "sys/operLog/operLogList";
    }
}
