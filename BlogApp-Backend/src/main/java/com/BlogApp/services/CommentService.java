package com.BlogApp.services;

import com.BlogApp.entities.Comment;

public interface CommentService {

	Comment createComment(Comment comment,String username,Integer postid);
	void deleteComment(String username,Integer commentid);
}
