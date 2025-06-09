package com.flwr.api.global.config;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flwr.api.common.dto.MessagePayloadDto;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd. a hh:mm:ss");
  public static final String RESET = "\u001B[0m";
  public static final String PURPLE = "\u001B[35m";
  public static final String CYAN = "\u001B[36m";
  public static final String YELLOW = "\u001B[33m";
  public static final String GREEN = "\u001B[32m";

  @Override
  public void registerWebSocketHandlers(@NonNull WebSocketHandlerRegistry registry) {
    registry.addHandler(simpleWebSocketHandler(), "/").setAllowedOrigins("*");
  }

  @Bean
  public WebSocketHandler simpleWebSocketHandler() {

    return new TextWebSocketHandler() {

      @Override
      public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        SocketAddress remoteAddr = session.getRemoteAddress();
        if (remoteAddr instanceof InetSocketAddress inetSocketAddress) {
          String ip = inetSocketAddress.getAddress().getHostAddress();
          int port = inetSocketAddress.getPort();

          String time = formatter.format(LocalDateTime.now());

          System.out.printf(
              "%s[Java]%s  - %s%s%s  %s[WS]%s [Connect] - %s:%d%n",
              PURPLE, RESET,
              CYAN, time, RESET,
              YELLOW, RESET,
              ip, port);
        }
      }

      @Override
      protected void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message)
          throws Exception {
        String payload = message.getPayload();
        System.out.println("MessageStr: " + payload);

        try {
          MessagePayloadDto jsonMessage = objectMapper.readValue(payload, MessagePayloadDto.class);
          if (jsonMessage == null) {
            session.sendMessage(new TextMessage("❌ Invalid Message Type"));
            return;
          }

          System.out.println(String.format("name: %s / age: %d", jsonMessage.getName(), jsonMessage.getAge()));
          session.sendMessage(new TextMessage("Echo: " + payload));

        } catch (Exception e) {
          e.printStackTrace();
          session.sendMessage(new TextMessage("❌ JSON Parse Error: " + e.getMessage()));
        }

      }
    };
  }
}
