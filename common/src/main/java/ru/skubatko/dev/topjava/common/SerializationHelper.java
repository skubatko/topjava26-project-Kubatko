package ru.skubatko.dev.topjava.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.experimental.UtilityClass;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.core.json.JsonReadFeature.ALLOW_TRAILING_COMMA;
import static com.fasterxml.jackson.databind.DeserializationFeature.*;
import static com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS;
import static com.fasterxml.jackson.databind.PropertyNamingStrategies.LOWER_CAMEL_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.*;

@UtilityClass
public class SerializationHelper {

    public ObjectMapper preconfiguredJsonObjectMapper() {
        ObjectMapper jsonObjectMapper = JsonMapper.builder()
                .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE,
                        READ_ENUMS_USING_TO_STRING,
                        ACCEPT_SINGLE_VALUE_AS_ARRAY,
                        ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                        ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)
                .enable(ACCEPT_CASE_INSENSITIVE_ENUMS)
                .enable(WRITE_ENUMS_USING_TO_STRING)
                .enable(ALLOW_TRAILING_COMMA.mappedFeature())
                .disable(FAIL_ON_EMPTY_BEANS,
                        WRITE_DATES_AS_TIMESTAMPS)
                .disable(ADJUST_DATES_TO_CONTEXT_TIME_ZONE,
                        FAIL_ON_UNKNOWN_PROPERTIES)
                .serializationInclusion(NON_NULL)
                .build();
        jsonObjectMapper.registerModule(new JavaTimeModule());
        jsonObjectMapper.registerModule(new Jdk8Module());
        jsonObjectMapper.registerModule(new Swagger2JacksonModule());
        jsonObjectMapper.setPropertyNamingStrategy(LOWER_CAMEL_CASE);
        return jsonObjectMapper;
    }
}
