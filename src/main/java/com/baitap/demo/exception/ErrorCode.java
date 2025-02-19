package com.baitap.demo.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
	EMPLOYEE_NOT_FOUND(1001, "Employee not found", HttpStatus.NOT_FOUND);
	Integer code;
	String message;
	HttpStatus httpStatus;
}
