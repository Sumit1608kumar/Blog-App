package com.BlogApp.controllers;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApp.dto.CommentDto;
import com.BlogApp.entities.Comment;
import com.BlogApp.exceptions.ApiResponse;
import com.BlogApp.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	CommentService commentService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping("/users/{username}/posts/{postid}/comments")
	public ResponseEntity<CommentDto> addNewComment(@RequestBody Comment comment,
			@PathVariable String username, @PathVariable Integer postid) {
		Comment createdComment = commentService.createComment(comment, username, postid);
		return new ResponseEntity<>(modelMapper.map(createdComment, CommentDto.class), HttpStatus.CREATED);
	}

	// delete single comment of a post
	@DeleteMapping("/users/{username}/posts/{postid}/comments/{commentid}")
	public ResponseEntity<ApiResponse> deleteCommentByCommentId(@PathVariable String username,
			@PathVariable Integer postid, @PathVariable Integer commentid) {

		commentService.deleteComment(username, commentid);
		ApiResponse apiResponse = new ApiResponse("Comment Successfully Deleted with id :" + commentid,
				LocalDateTime.now(), HttpStatus.OK, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}
}
