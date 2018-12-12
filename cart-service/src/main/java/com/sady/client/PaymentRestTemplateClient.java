package com.sady.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sady.bean.Card;

@Component
public class PaymentRestTemplateClient {

	@Autowired
	private RestTemplate restTemplate;
	
	public Card fetchCardDetails(int cardId){
		
		/* ***************Using getForObject ******************************* */
		//return restTemplate.getForObject("http://payment-service/card/1", Card.class);
		
		
		/* ***************Using varibles ******************************* */
		/*ResponseEntity<Card> responseEntity = restTemplate.exchange("http://payment-service/card/{cardId}", 
				HttpMethod.GET, null, Card.class, cardId);*/
		
		
		/* ***************Using params ******************************* */
		Map<String, Integer> params = new HashMap<>();
		params.put("cardId", 1);
		
		ResponseEntity<Card> responseEntity = restTemplate.exchange("http://payment-service/card/{cardId}", 
				HttpMethod.GET, null, Card.class, params);
		
		return responseEntity.getBody();
	
	}
}
