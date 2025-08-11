package com.weverse.sb.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.weverse.sb.exception.ErrorResponseDto;
import com.weverse.sb.user.entity.User;
import com.weverse.sb.user.enums.Role;
import com.weverse.sb.user.repository.UserRepository;
import com.weverse.sb.user.service.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper = new ObjectMapper() // JSON 변환용
    .registerModule(new JavaTimeModule())               // Java 8 time 모듈 등록
    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // ISO-8601 문자열로 출력

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  JwtUtil jwtUtil,
                                  UserRepository userRepository) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String path = request.getRequestURI();

        // ✅ 인증이 필요 없는 경로는 필터를 통과시킴
        if (path.startsWith("/api/auth") || path.startsWith("/public")) {
            chain.doFilter(request, response);
            return;
        }

        // ✅ Authorization 헤더 확인
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response); // 토큰 없으면 무시하고 다음 필터로
            return;
        }

        String token = header.replace("Bearer ", "");

        try {
            // ✅ 유효성 검사 + 이메일/권한 추출
            String email = jwtUtil.validateTokenAndGetEmail(token); // → ExpiredJwtException 가능
            Role role = jwtUtil.getRoleFromToken(token);

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

            CustomUserDetails userDetails = new CustomUserDetails(user);

            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authToken);

            chain.doFilter(request, response); // 다음 필터로 넘김
            return;

        } catch (ExpiredJwtException e) {
            writeJsonErrorResponse(response, HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.", request.getRequestURI());
            return;

        } catch (JwtException | IllegalArgumentException e) {
            writeJsonErrorResponse(response, HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.", request.getRequestURI());
            return;

        } catch (UsernameNotFoundException e) {
            writeJsonErrorResponse(response, HttpStatus.UNAUTHORIZED, "사용자를 찾을 수 없습니다.", request.getRequestURI());
            return;
        }
    }

    // ✅ 공통 JSON 응답 메서드
    private void writeJsonErrorResponse(HttpServletResponse response,
                                        HttpStatus status,
                                        String message,
                                        String path) throws IOException {

        ErrorResponseDto errorResponse = ErrorResponseDto.of(status, message, path);

        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), errorResponse);
        // response.flushBuffer(); // 즉시 출력
    }
}