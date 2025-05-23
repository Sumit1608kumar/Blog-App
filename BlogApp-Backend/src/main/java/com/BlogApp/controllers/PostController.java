package com.BlogApp.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tika.Tika;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BlogApp.constants.GlobalConstants;
import com.BlogApp.dto.PostDto;
import com.BlogApp.dto.PostResponseDto;
import com.BlogApp.entities.Post;
import com.BlogApp.exceptions.ApiResponse;
import com.BlogApp.exceptions.CustomException;
import com.BlogApp.services.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ModelMapper modelMapper;

	// CREATE NEW POST [ USING FORM DATA I.E, INPUTS+FILE ]
	@PostMapping("/users/{username}/posts/{categoryname}")
	public ResponseEntity<PostDto> createNewPostWithFormData(@RequestParam String post,
			@RequestParam(name = "image", required = false) MultipartFile file,
			@PathVariable String username, @PathVariable String categoryname) {

		Post createdpost = null;
		try {
			Post postdata = objectMapper.readValue(post, Post.class);
			createdpost = postService.createPostAndSaveImageInDB(postdata, username, categoryname, file);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<PostDto>(modelMapper.map(createdpost, PostDto.class), HttpStatus.CREATED);
	}

	// ADD IMAGE TO A POST
	@PostMapping("/users/{username}/posts/{postid}/image")
	public ResponseEntity<ApiResponse> addImageToPost(@RequestParam MultipartFile image,
			@PathVariable String username, @PathVariable Integer postid) {

		postService.addImageToPost(image, username, postid);
		ApiResponse apiResponse = new ApiResponse("Image Successfully Added To The Post With Id :" + postid,
				LocalDateTime.now(), HttpStatus.OK, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	// SERVE POST IMAGE
	@GetMapping(value = "/images/servepostimage/{postid}")
	public ResponseEntity<byte[]> servePostImage(@PathVariable Integer postid) {
		Post foundPost = postService.getPostById(postid);
		if (foundPost.getImage().equals(GlobalConstants.DEFAULT_POST_IMAGE_NAME)) {
			throw new CustomException("Default Post Image is Set , Will Be Taken from frontend : "
					+ GlobalConstants.DEFAULT_POST_IMAGE_NAME, HttpStatus.OK);
		}
		// Detect MIME type of image data
		String contentType = new Tika().detect(foundPost.getImageData());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType(contentType));
		return new ResponseEntity<>(foundPost.getImageData(), headers, HttpStatus.OK);
	}

	// GET ALL POSTS OF USER BY USERNAME
	@GetMapping("/users/{username}/posts")
	public ResponseEntity<List<PostDto>> getPostByUsername(@PathVariable String username,
			@RequestParam(defaultValue = "true", required = false) Boolean mostrecentfirst) {
		List<PostDto> allPostsByUser = postService.getAllPostsByUser(username, mostrecentfirst).stream()
				.map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return new ResponseEntity<List<PostDto>>(allPostsByUser, HttpStatus.OK);
	}

	// GET SINGLE POST OF USER BY PID
	@GetMapping("/users/{username}/posts/{postid}")
	public ResponseEntity<PostDto> getPostOfUserByPostId(@PathVariable String username,
			@PathVariable Integer postid) {
		Post post = postService.getPostById(postid);
		return new ResponseEntity<PostDto>(modelMapper.map(post, PostDto.class), HttpStatus.OK);
	}

	// DELETE SINGLE POST OF USER BY PID
	@DeleteMapping("/users/{username}/posts/{postid}")
	public ResponseEntity<ApiResponse> deletePostOfUserByPostId(@PathVariable String username,
			@PathVariable Integer postid) {
		postService.deletePostById(postid);
		ApiResponse apiResponse = new ApiResponse("Post Successfully Deleted with id :" + postid, LocalDateTime.now(),
				HttpStatus.OK, HttpStatus.OK.value());
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	// UPDATE SINGLE POST OF USER BY PID
	@PutMapping("/users/{username}/posts/{postid}")
	public ResponseEntity<PostDto> updatePostOfUserByPostId(@PathVariable String username,
			@PathVariable Integer postid, @RequestBody Post newpostdata) {
		Post updatedpost = postService.updatePostById(newpostdata, postid, username);

		return new ResponseEntity<PostDto>(modelMapper.map(updatedpost, PostDto.class), HttpStatus.OK);
	}

	// GET POST BY POSTID
	@GetMapping("/posts/{postid}")
	public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer postid) {
		Post post = postService.getPostById(postid);
		return new ResponseEntity<PostDto>(modelMapper.map(post, PostDto.class), HttpStatus.OK);
	}

	// http://localhost:8080/api/posts/category/CSE
	// GET ALL POST BY CATEGORY
	@GetMapping("/posts/category/{categoryname}")
	public ResponseEntity<PostResponseDto> getAllPostsByCategory(@PathVariable String categoryname,
			@RequestParam(defaultValue = "0", required = false) Integer pagenumber,
			@RequestParam(defaultValue = "5", required = false) Integer pagesize,
			@RequestParam(defaultValue = "true", required = false) boolean mostrecentfirst) {
		PostResponseDto allPostsByCategory = postService.getAllPostsByCategory(categoryname, pagenumber, pagesize,
				mostrecentfirst);
		return new ResponseEntity<PostResponseDto>(allPostsByCategory, HttpStatus.OK);
	}

	// GET ALL POSTS
	@GetMapping("/posts")
	public ResponseEntity<PostResponseDto> getAllPosts(
			@RequestParam(defaultValue = "0", required = false) Integer pagenumber,
			@RequestParam(defaultValue = "5", required = false) Integer pagesize,
			@RequestParam(defaultValue = "true", required = false) boolean mostrecentfirst) {
		PostResponseDto allPosts = postService.getAllPosts(pagenumber, pagesize, mostrecentfirst);

		return new ResponseEntity<>(allPosts, HttpStatus.OK);
	}

}
