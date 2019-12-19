package com.lovelive.sys.utils;

import com.lovelive.common.uitls.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Description 全局配置类
 * static变量需要用set注入，并且set方法不能是static
 * @Author dHe
 * @Date 2019/5/5
 */
@Component
@PropertySource("customConfig.properties")
public class Global {

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

    /**
     * 文件存放路径
     */
    private static String filePath;

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

    public static String getFilePath() {
        if (StringUtils.isBlank(filePath)) {
            filePath = System.getProperty("user.dir");
        }
        return filePath;
    }

    @Value("${file.path}")
    public void setFilePath(String filePath) {
        Global.filePath = filePath;
    }
}
