package com.BlogApp.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
	private Integer cid;
	private String comment;
	private Date commentdate;
	private UserDto user;
}
