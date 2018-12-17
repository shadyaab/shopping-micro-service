package com.sady.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentsController {
	
	@RequestMapping("/getComment")
	public String fetchComment(){
		return "comment return";
	}

}
