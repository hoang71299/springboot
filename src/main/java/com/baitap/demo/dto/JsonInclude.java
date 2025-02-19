package com.baitap.demo.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonInclude {

	public static<T> ResponseEntity<?> ok(T body) {
		return ResponseEntity.ok().body(body);
	}
	public static <T> ResponseEntity<?> created(T body) {
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}
	public static <T> ResponseEntity<?> noContent() {
		return ResponseEntity.noContent().build();
	}
}
