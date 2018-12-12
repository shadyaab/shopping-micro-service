package com.sady.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sady.bean.Card;

@RestController
public class PaymentController {
	
	@RequestMapping("/makepayment/{cardId}")
	public String makePayment(@PathVariable int cardId) throws InterruptedException{
		
		Thread.sleep(4000);
		
		System.out.println("CardId : " + cardId);
		
		return "Payment Done Successfully for carId : " + cardId ;
	}
	
	
	@RequestMapping(value= "/card/{cardId}")
	public Card getCardDetails(@PathVariable int cardId){
		
		Card card = new Card();
		card.setCardId(cardId);
		card.setCardName("ICICI");
		card.setExpYear(2021);
		
		return card;
	}
	
	
}
