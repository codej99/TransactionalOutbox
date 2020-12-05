package com.kakaowebtoon.outbox.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.kakaowebtoon.outbox.infrastructure.adapter.LicenseEntity;

import java.time.format.DateTimeFormatter;

public class EventUtils {

    private EventUtils() {
    }

    public enum AggregateType {
        LICENCE
    }

    public enum EventType {
        INSERT, UPDATE, DELETE
    }

    public static OutBoxEvent createLicenseEvent(LicenseEntity license, EventType eventType) {
        ObjectMapper mapper = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")));
        mapper.registerModule(javaTimeModule); // LocalDateTime을 Json으로 출력시 format 지정
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE); // java 객체의 property name을 DB 컬럼명에 맞게 snake_case로 출력
        JsonNode jsonNode = mapper.convertValue(license, JsonNode.class);
        return OutBoxEvent.builder()
                .aggregateId(license.getUserid())
                .aggregateType(AggregateType.LICENCE)
                .eventType(eventType)
                .payload(jsonNode).build();
    }
}
