package com.timmy.utils.common;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JSONUtil {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);
    
    /**
     * 该方法将java对象（<code>obj</code>）转化成对应的json字符串
     * 
     * @param obj 对象
     * @return JSON字符串
     * @throws JsonProcessingException
     */
    public static String obj2json(Object obj) throws JsonProcessingException
 {
        return OBJECT_MAPPER.writeValueAsString(obj);
    }
    
    /**
     * 该方法将JSON格式字符（<code>jsonStr</code>）串转化成指定类型（<code>clazz</code>）的对象
     * 
     * @param jsonStr JSON格式字符串
     * @param clazz Class对象
     * @return java对象
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public static <T> T json2Object(String jsonStr, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return OBJECT_MAPPER.readValue(jsonStr, clazz);
    }
    
    public static <T> List<T> json2List(String jsonStr,Class<?> collectionClazz, Class<T> elementClazz) throws JsonParseException, JsonMappingException, IOException {
        return OBJECT_MAPPER.readValue(jsonStr, OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClazz, elementClazz));
    }
}
