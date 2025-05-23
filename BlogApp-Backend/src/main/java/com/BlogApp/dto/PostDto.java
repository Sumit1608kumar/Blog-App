package com.BlogApp.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private int pid;
	private String title;
	private String content;
	private String image;
	private Date date;
	private byte[] imageData;
	private CategoryDto category;
	private UserDto user;
	private List<CommentDto> comments;
}
