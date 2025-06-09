package com.flwr.api.global.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Order(1)
public class RequestLoggingFilter implements Filter {

  private static final String RESET = "\u001B[0m";
  private static final String PURPLE = "\u001B[35m";
  private static final String CYAN = "\u001B[36m";
  private static final String YELLOW = "\u001B[33m";
  private static final String GREEN = "\u001B[32m";
  private static final String RED = "\u001B[31m";
  // private static final String GRAY = "\u001B[90m";
  // private static final String BOLD = "\u001B[1m";

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. MM. dd. a hh:mm:ss");

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    long start = System.currentTimeMillis();

    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse res = (HttpServletResponse) response;

    chain.doFilter(request, response);

    long duration = System.currentTimeMillis() - start;

    String method = req.getMethod();
    String uri = req.getRequestURI();
    int status = res.getStatus();
    String time = formatter.format(LocalDateTime.now());
    String remoteAddr = req.getRemoteAddr();
    int port = req.getRemotePort();

    String statusColor = status >= 200 && status < 300 ? GREEN
        : status >= 400 && status < 500 ? YELLOW
            : RED;

    System.out.printf(
        "%s[Java]%s  - %s%s%s  %s[HTTP]%s %s %s +%dms %s%d%s - %s:%d%n",
        PURPLE, RESET,
        CYAN, time, RESET,
        YELLOW, RESET,
        method,
        uri,
        duration,
        statusColor, status, RESET,
        remoteAddr, port);
  }
}
