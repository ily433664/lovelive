package com.lovelive.test;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lovelive.modules.sys.entity.Message;
import com.lovelive.modules.sys.entity.User;
import com.lovelive.modules.sys.enums.MessageTypeEnums;

import java.util.Map;

public class JacksonTest {

    public static void main(String[] args) {

        try {
            Message obj = new Message();
            obj.setType(MessageTypeEnums.SYSTEM.getValue());
            obj.setReady(false);
            obj.setReadTime(null);
            obj.setSender(null);
            obj.setRecipient(new User());
            obj.setTitle("系统通知");
            obj.setContent("");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);   // 忽略null属性

            System.out.println(objectMapper.writeValueAsString(obj));   // 转为 json 字符串

            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));    // 转为格式化的 json 字符串

            Message obj2 = objectMapper.readValue(objectMapper.writeValueAsString(obj), Message.class); // 解析 json 字符串为对象

            Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(obj), Map.class);  // 解析 json 字符串为 map

            System.out.println("end ... ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
