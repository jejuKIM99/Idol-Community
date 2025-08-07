package com.weverse.sb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ✅ JWT 토큰 만료
    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredJwt(ExpiredJwtException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
        		.body(ErrorResponseDto.of(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.", request.getRequestURI())
        	    );
    }

    // ✅ 인증/인가 실패
    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ErrorResponseDto.of(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.", request.getRequestURI()));
    }

    // ✅ 유저 없음
    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFound(UsernameNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorResponseDto.of(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다.", request.getRequestURI()));
    }

    // ✅ 잘못된 입력값 (DTO 검증 실패)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, "입력값 검증 실패", request.getRequestURI(), ex.getBindingResult()));
    }

    // ✅ 그 외 에러
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleOther(Exception ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다.", request.getRequestURI()));
    }
    
    // ✅ 닉네임 중복 등 클라이언트 잘못
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponseDto.of(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getRequestURI()));
    }

}

