package com.flwr.api.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson 직렬화/역직렬화 설정
 *
 * - LocalDate, LocalDateTime을 ISO 포맷으로 처리
 * - timestamp 대신 문자열로 날짜 출력
 * - JavaTimeModule 등록
 */
@Configuration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {
    return createObjectMapper();
  }

  public static ObjectMapper createObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();

    // Java 8 Time (LocalDate 등) 직렬화 지원
    objectMapper.registerModule(new JavaTimeModule());

    // 날짜를 문자열(ISO-8601)로 출력하도록 설정 (timestamp 비활성화)
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // Unknown 필드 무시 (선택)
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    // 필요 시 Java 8 타입 지원 관련 옵션도 명시
    objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

    return objectMapper;
  }
}
