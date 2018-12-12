package com.sady.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.sady.bean.Card;
import com.sady.service.ServiceDiscoveryService;

@RestController
public class CartController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private ServiceDiscoveryService service;
	
	
	/* **********************************Hystrix******************************************** */
	
	@HystrixCommand(fallbackMethod="getFallbackOrder")
	@RequestMapping("/buy/handleExp")
	public String makeOrder(){
		String msg = "";
		try{	
			msg = restTemplate.getForObject("http://payment-service/makepayment/1", String.class);
			System.out.println("Msg from payment service : " + msg);
		} catch ( HystrixRuntimeException ex){
			msg = "Inside Hystrix Runtime Exception catch block- call to payment-service failed";
			System.out.println(msg);
		}
		
		return msg;
	}
	
	@HystrixCommand(
			fallbackMethod="getFallbackOrder",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="6000")})
	
	@RequestMapping("/buy")
	public String makeOrder1(){
		
		String msg = restTemplate.getForObject("http://payment-service/makepayment/1", String.class);
		System.out.println("Msg from payment service : " + msg);
		System.out.println("Username: " + env.getProperty("cart.name"));
		
		return msg;
	}
	
	public String getFallbackOrder(){
		System.out.println("Fallback occur");
		
		return "Payment cannot be processed because of some technical issues";
	}
	
	
	
	/* ****************************Client Discovery Using Ribbon********************************** */
	
	/**
	 * Spring Discovery Client
	 */
	@RequestMapping("/getCard/discovery")
	public Card getCardDetailsUsingDisvovery(){
		
		Card card = service.fetchCardDetails(1, "discovery");
		System.out.println(card);
		
		return card;
	}
	
	/**
	 * Spring Discovery Client enabled RestTemplate (Rest)
	 */
	@RequestMapping("/getCard/rest")
	public Card getCardDetailsUsingRest(){
		
		Card card = service.fetchCardDetails(1, "rest");
		System.out.println(card);
		
		return card;
	}
	
	
	/**
	 * Feign Client (Feign)
	 */
	@RequestMapping("/getCard/feign")
	public Card getCardDetailsUsingFeign(){
		
		Card card = service.fetchCardDetails(2, "feign");
		System.out.println(card);
		
		return card;
	}
	
	
	
}











