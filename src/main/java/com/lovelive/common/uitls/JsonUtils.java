package com.lovelive.common.uitls;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * json 工具类
 *
 * @author dHe
 */
public class JsonUtils {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);   // 忽略null属性
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectMapper getObjectMapper(JsonInclude.Include incl) {
        return new ObjectMapper().setSerializationInclusion(incl);
    }

    public static ObjectMapper getAnyObjectMapper() {
        return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    public static ObjectMapper getNewObjectMapper() {
        return new ObjectMapper();
    }

    /**
     * 对象转为 json
     *
     * @param value
     * @param objectMapper
     * @return
     */
    public static String toString(Object value, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转为 json
     *
     * @param value
     * @return
     */
    public static String toString(Object value) {
        return toString(value, objectMapper);
    }

    /**
     * 对象转为 json
     *
     * @param value
     * @param objectMapper
     * @return
     */
    public static String toPrettyString(Object value, ObjectMapper objectMapper) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 对象转为 json
     *
     * @param value
     * @return
     */
    public static String toPrettyString(Object value) {
        return toPrettyString(value, objectMapper);
    }

    /**
     * 字符串转为 JsonNode
     *
     * @param content
     * @param objectMapper
     * @return
     */
    public static JsonNode toJsonNode(String content, ObjectMapper objectMapper) {
        try {
            return objectMapper.readTree(content);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转为 JsonNode
     *
     * @param content
     * @return
     */
    public static JsonNode toJsonNode(String content) {
        return toJsonNode(content, objectMapper);
    }

    /**
     * 字符串转为 Map
     *
     * @param content
     * @param objectMapper
     * @return
     */
    public static Map toMap(String content, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(content, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转为 Map
     *
     * @param content
     * @return
     */
    public static Map toMap(String content) {
        return toMap(content, objectMapper);
    }

    /**
     * JsonNode 转为 Map
     *
     * @param jsonNode
     * @param objectMapper
     * @return
     */
    public static Map toMap(JsonNode jsonNode, ObjectMapper objectMapper) {
        try {
            return toObject(jsonNode, Map.class, objectMapper);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * JsonNode 转为 Map
     *
     * @param jsonNode
     * @return
     */
    public static Map toMap(JsonNode jsonNode) {
        return toMap(jsonNode, objectMapper);
    }

    /**
     * 字符串转为对象
     *
     * @param content
     * @param valueType
     * @param objectMapper
     * @param <T>
     * @return
     */
    public static <T> T toObject(String content, Class<T> valueType, ObjectMapper objectMapper) {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 字符串转为对象
     *
     * @param content
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T toObject(String content, Class<T> valueType) {
        return toObject(content, valueType, objectMapper);
    }

    /**
     * JsonNode 转为对象
     *
     * @param jsonNode
     * @param valueType
     * @param objectMapper
     * @param <T>
     * @return
     */
    public static <T> T toObject(JsonNode jsonNode, Class<T> valueType, ObjectMapper objectMapper) {
        try {
            return objectMapper.treeToValue(jsonNode, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * JsonNode 转为对象
     *
     * @param jsonNode
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T toObject(JsonNode jsonNode, Class<T> valueType) {
        return toObject(jsonNode, valueType, objectMapper);
    }
}
