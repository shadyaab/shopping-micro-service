package com.sady.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
	
	@RequestMapping("/makepayment/{cardId}")
	public String makePayment(@PathVariable int cardId){
		
		System.out.println("CardId : " + cardId);
		
		return "Payment Done Successfully for carId : " + cardId ;
	}
	
}
