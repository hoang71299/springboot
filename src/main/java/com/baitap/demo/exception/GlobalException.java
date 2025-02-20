package com.baitap.demo.exception;


import com.baitap.demo.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler
	public ResponseEntity<ApiResponse<?>> handleApiException(ApiException e) {
		ErrorCode errorCode = e.getErrorCode();
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiResponse.builder()
						.code(errorCode.getCode())
						.message(errorCode.getMessage()).build());
	}
}
