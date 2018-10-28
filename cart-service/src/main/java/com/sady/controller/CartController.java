package com.sady.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CartController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod="getFallbackOrder")
	@RequestMapping("/buy")
	public String makeOrder(){
		
		String msg = restTemplate.getForObject("http://payment-service/makepayment/1", String.class);
		System.out.println("Msg from payment service : " + msg);
		
		return msg;
	}
	
	public String getFallbackOrder(){
		System.out.println("Fallback occur");
		
		return "Payment cannot be processed because of some technical issues";
	}
	
}