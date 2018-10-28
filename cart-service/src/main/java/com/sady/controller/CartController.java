package com.sady.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CartController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/buy")
	public String makeOrder(){
		
		String msg = restTemplate.getForObject("http://payment-service/makepayment/1", String.class);
		System.out.println("Msg from payment service : " + msg);
		
		return msg;
	}
	
}
