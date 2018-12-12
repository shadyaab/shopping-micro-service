package com.sady.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sady.bean.Card;
import com.sady.client.PaymentDiscoveryClient;
import com.sady.client.PaymentFeignClient;
import com.sady.client.PaymentRestTemplateClient;

@Service
public class ServiceDiscoveryService {

	@Autowired
	private PaymentDiscoveryClient discoveryClient;
	
	@Autowired
	private PaymentRestTemplateClient restClient;
	
	@Autowired
	private PaymentFeignClient feignClient;
	
	public Card fetchCardDetails(int cardId, String type){
		
		if(type.equalsIgnoreCase("discovery")){
			return discoveryClient.fetchCardDetails(cardId);
		} else if (type.equalsIgnoreCase("rest")){
			return restClient.fetchCardDetails(cardId);
		} else if(type.equalsIgnoreCase("feign")){
			return feignClient.fetchCardDetails(cardId);
		}
		
		return null;
		
	}
	
}
