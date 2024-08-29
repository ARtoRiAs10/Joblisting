package com.example.joblisting.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.joblisting.model.Post;
import com.example.joblisting.repository.PostRepository;
import com.example.joblisting.repository.SearchRepository;

import springfox.documentation.annotations.ApiIgnore;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
	
	@Autowired
	PostRepository repo;
	
	@Autowired
	SearchRepository srepo;

	@ApiIgnore
	@RequestMapping(value="/")
	public void redirect(HttpServletRequest response) throws IOException {
		response.sendRedirect("/swagger-ui.html");
	}
	
	@GetMapping("/allPosts")
	@CrossOrigin
	public List<Post>  getAllPosts()
	{
		return repo.findAll();
	}
	
	@PostMapping("/post")
	@CrossOrigin
	public Post addPost(@RequestBody Post post) {
		return repo.save(post);
	}
	// posts/java
	
	
	@GetMapping("/posts/{text}")
	@CrossOrigin
	public List<Post> search(@PathVariable String text) {
		return  srepo.findByText(text);
	}
}
