package com.baitap.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GrettingController {
	@GetMapping
	public ResponseEntity<String> getGreeting(@RequestParam(defaultValue = "") String name) {
		return ResponseEntity.ok("Hello " + name+"!!!");
	}
}
