package com.lovelive.sys.base;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dhe
 * @version V1.0
 * @ClassName: BaseController
 * @Description: 控制器支持类
 * @date 2018年1月18日 下午5:58:22
 */

public abstract class BaseController {

    /**
     * 添加Model消息
     *
     * @param messages
     */
    protected void addMessage(Model model, String... messages) {
        List<String> messageList = new ArrayList<>();
        for (int i = 0; i < messages.length; i++) {
            messageList.add(messages[i]);
        }
        model.addAttribute("message", messageList);
    }

    /**
     * 添加Flash消息
     *
     * @param messages
     */
    protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
        List<String> messageList = new ArrayList<>();
        for (int i = 0; i < messages.length; i++) {
            messageList.add(messages[i]);
        }
        redirectAttributes.addFlashAttribute("message", messageList);
    }

    protected void addMessage(RedirectAttributes redirectAttributes, List<String> messages) {
        redirectAttributes.addFlashAttribute("message", messages);
    }

    protected void populate(Object obj, HttpServletRequest request) {
        try {
            BeanUtils.populate(obj, request.getParameterMap());
        } catch (Exception e) {
            //	e.printStackTrace();
        }
    }

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }

            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml(text.trim()));
            }
        });
    }
}
