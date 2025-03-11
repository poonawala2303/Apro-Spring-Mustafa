package com.aurionpro.employee.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class PageResponse <T>
{
	private int totalPages;
	private int pageSize;
	private long totalElements;
	private boolean isLast;
	private List<T> content;
	
}
