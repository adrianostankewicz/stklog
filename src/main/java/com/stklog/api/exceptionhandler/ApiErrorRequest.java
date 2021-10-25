package com.stklog.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorRequest {

	private Integer status;
	private OffsetDateTime dateTime;
	private String title;
	private List<Field> fields;
	
	@AllArgsConstructor
	@Getter
	public static class Field {
		
		private String name;
		private String message;
	}
}
