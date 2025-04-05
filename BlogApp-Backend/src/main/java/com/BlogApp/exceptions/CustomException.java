package com.BlogApp.exceptions;

import java.io.Serial;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
	private String message;
	private LocalDateTime timestamp;
	private HttpStatus status;
	
	public CustomException(String message,HttpStatus status)
	{
		this.message=message;
		this.timestamp=LocalDateTime.now();
		this.status=status;
	}
}
