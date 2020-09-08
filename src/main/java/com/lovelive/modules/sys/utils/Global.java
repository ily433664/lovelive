package com.lovelive.modules.sys.utils;

import com.lovelive.common.uitls.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 全局配置类
 * static变量需要用set注入，并且set方法不能是static
 *
 * @author dHe
 */
@Component
@PropertySource("classpath:custom-config.properties")
public class Global {

    /**
     * 文件存放路径
     */
    private static String filePath;

    /**
     * 返回数据显示说明
     */
    private static boolean responseExplainDisplay;

    /**
     * 验证码位数
     */
    private static int verifySize;

    /**
     * 验证码图片宽度
     */
    private static int verifyWidth;

    /**
     * 验证码图片高度
     */
    private static int verifyHeight;

    public static String getFilePath() {
        if (StringUtils.isBlank(filePath)) {
            filePath = System.getProperty("user.dir");
            System.out.println("filePath is blank, user.dir : " + filePath);
        }
        return filePath;
    }

    @Value("${file.path}")
    public void setFilePath(String filePath) {
        Global.filePath = filePath;
    }

    public static boolean isResponseExplainDisplay() {
        return responseExplainDisplay;
    }

    @Value("${response.explain.display}")
    public void setResponseExplainDisplay(boolean responseExplainDisplay) {
        Global.responseExplainDisplay = responseExplainDisplay;
    }

    public static int getVerifySize() {
        return verifySize;
    }

    @Value("${verify.size:4}")
    public void setVerifySize(int verifySize) {
        Global.verifySize = verifySize;
    }

    public static int getVerifyWidth() {
        return verifyWidth;
    }

    @Value("${verify.width:90}")
    public void setVerifyWidth(int verifyWidth) {
        Global.verifyWidth = verifyWidth;
    }

    public static int getVerifyHeight() {
        return verifyHeight;
    }

    @Value("${verify.height:30}")
    public void setVerifyHeight(int verifyHeight) {
        Global.verifyHeight = verifyHeight;
    }

}
