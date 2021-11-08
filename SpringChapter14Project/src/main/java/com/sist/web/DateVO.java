package com.sist.web;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DateVO {
	@DateTimeFormat(pattern="yyyyMMddHH")
    private LocalDateTime from;
	@DateTimeFormat(pattern="yyyyMMddHH")
    private LocalDateTime to;
	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	
}
