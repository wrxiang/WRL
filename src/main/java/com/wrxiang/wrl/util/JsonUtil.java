package com.wrxiang.wrl.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.apache.commons.lang3.StringUtils;

import java.io.*;

/**
 * @Description JSON格式化工具类
 * @Author wrxiang
 * @Date 2021/12/12 12:14
 */
public class JsonUtil {

    public static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
                jgen.writeString("");
            }
        });
    }

    public static <T> T parse(String value, Class<T> clz) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return mapper.readValue(value, clz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T parse(byte[] bytes, Class<T> clz) {
        try {
            return mapper.readValue(bytes, clz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public static <T> T parse(InputStream ins, Class<T> clz) {
        try {
            return mapper.readValue(ins, clz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }


    public static <T> T parse(Reader reader, Class<T> clz) {
        try {
            return mapper.readValue(reader, clz);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }


    @SuppressWarnings("unchecked")
    public static <T> T parse(String value, JavaType javaType) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return (T) mapper.readValue(value, javaType);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static <T> T parse(String value, TypeReference valueTypeRef) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        try {
            return (T) mapper.readValue(value, valueTypeRef);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T update(String value, T object) {
        try {
            return (T) mapper.readerForUpdating(object).readValue(value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static String writeValueAsString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void write(OutputStream outs, Object o) {
        try {
            mapper.writeValue(outs, o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static void write(Writer writer, Object o) {
        try {
            mapper.writeValue(writer, o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static String toString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static byte[] toBytes(Object o) {
        try {
            return mapper.writeValueAsBytes(o);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }




}