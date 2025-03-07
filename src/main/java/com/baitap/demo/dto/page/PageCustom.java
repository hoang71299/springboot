package com.baitap.demo.dto.page;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageCustom<T>{
	int page;
	int size;
	int totalElements;
	int totalPages;

	public PageCustom(Page<T> page) {
		this.page = page.getNumber();
		this.size = page.getSize();
		this.totalElements = page.getTotalPages();
		this.totalPages = page.getNumberOfElements();
	}
}
