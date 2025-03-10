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
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
	public List<T> getContent() {
		return content;
	}
	public void setContent(List<T> content) {
		this.content = content;
	}
	public PageResponse(int totalPages, int pageSize, long totalElements, boolean isLast, List<T> content) {
		super();
		this.totalPages = totalPages;
		this.pageSize = pageSize;
		this.totalElements = totalElements;
		this.isLast = isLast;
		this.content = content;
	}
	public PageResponse() {
		super();
	}
	
	
	
	
}
