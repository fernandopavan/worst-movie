package com.myorg.worst_movie.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class StandardError implements Serializable {

	private Long timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	
	public StandardError(Long timestamp, Integer status, String error, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

}
