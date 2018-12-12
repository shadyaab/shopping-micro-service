package com.sady.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sady.bean.Card;

@Component
public class PaymentDiscoveryClient {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	public Card fetchCardDetails(int cardId){
		
		RestTemplate restTemplate = new RestTemplate();
		List<ServiceInstance> instances = discoveryClient.getInstances("payment-service");
		
		if(instances.size() == 0){
			System.out.println("No instance Found !!!");
		}
		
		String serviceUri = String.format("%s/card/%s",instances.get(0).getUri().toString(), cardId);
		
		ResponseEntity<Card> responseEntity = restTemplate.exchange(serviceUri, HttpMethod.GET,
				null, Card.class, cardId);
	
		return responseEntity.getBody();
	}

}
