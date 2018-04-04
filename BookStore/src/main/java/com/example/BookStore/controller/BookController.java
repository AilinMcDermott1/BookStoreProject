package com.example.BookStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.BookStore.repository.BookRepository;
import com.example.BookStore.repository.UserRepository;

@Controller
public class BookController {
	
	@Autowired
	UserRepository userR; 
	
	@Autowired
	BookRepository bookR; 
	
	@RequestMapping(value= {"/login"})
	public String login() {
		return "login"; 
	}
	
	@RequestMapping("/")
	public String home() {
		return "home"; 
	}

}
