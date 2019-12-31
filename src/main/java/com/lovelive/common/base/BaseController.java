package com.lovelive.common.base;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

/**
 * controller支持类
 *
 * @author dHe
 * @date 2019-4-26
 */
public abstract class BaseController {

    /**
     * 日志对象
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 添加 Model 消息
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
     * 添加 Flash 消息
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
     * 1. 将所有传递进来的 String 进行 HTML 编码，防止 XSS 攻击
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        // String 类型转换，将所有传递进来的 String 进行 HTML 编码，防止 XSS 攻击
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public String getAsText() {
                Object value = getValue();
                return value != null ? value.toString() : "";
            }

            @Override
            public void setAsText(String text) {
                setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
            }
        });
    }
}
