package com.baitap.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
	private final Map<String,String> dictionaryMap = Map.ofEntries(
			Map.entry("hello", "xin chao"),
			Map.entry("goodbye", "tam biet"),
			Map.entry("yes", "co"),
			Map.entry("no", "khong")
	);

	@GetMapping
	public ResponseEntity<String> dictionary(@RequestParam(defaultValue = "") String word) {
		String translation = dictionaryMap.get(word.trim().toLowerCase());
		if(translation != null){
			return ResponseEntity.ok(translation);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Khong tim duoc chu vua nhap");
	}




}
