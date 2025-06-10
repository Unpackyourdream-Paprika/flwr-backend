package com.flwr.api.global.filter;

import com.flwr.api.global.jwt.JwtProvider;
import com.flwr.api.user.domain.User;
import com.flwr.api.user.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtProvider jwtProvider;
  private final UserService userService;

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                  @NonNull FilterChain filterChain)
          throws ServletException, IOException {

    String token = jwtProvider.resolveToken(request);

    if (token != null && jwtProvider.validateToken(token)) {
      String userIdStr = jwtProvider.getUserId(token);

      long userId;
      try {
        userId = Long.parseLong(userIdStr);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(e.getMessage());
      }

      User user = userService.getUserInfoById(userId);

      UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
              Collections.emptyList());

      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }
}
