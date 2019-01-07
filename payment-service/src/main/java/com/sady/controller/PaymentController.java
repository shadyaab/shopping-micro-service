package com.sady.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sady.bean.Card;

@RestController
public class PaymentController {
	
	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@RequestMapping("/makepayment/{cardId}")
	public String makePayment(@PathVariable int cardId) throws InterruptedException{
		
		logger.info("Inside makePayment method of Payment controller" );
		Thread.sleep(4000);
		
		System.out.println("CardId : " + cardId);
		
		logger.info("Excution of makePayment method ends" );
		return "Payment Done Successfully for carId : " + cardId ;
	}
	
	
	@RequestMapping(value= "/card/{cardId}")
	public Card getCardDetails(@PathVariable int cardId){
		
		logger.info("Inside getCarDetails method of Payment controller" );
		
		Card card = new Card();
		card.setCardId(cardId);
		card.setCardName("ICICI");
		card.setExpYear(2021);
		
		logger.info("Excution of getCarDetails method ends" );
		return card;
	}
	
	
}
