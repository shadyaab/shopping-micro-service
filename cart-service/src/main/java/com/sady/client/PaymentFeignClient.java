package com.sady.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sady.bean.Card;

@FeignClient("payment-service")
public interface PaymentFeignClient {
	
	@RequestMapping(value="/card/{cardId}")
	public Card fetchCardDetails(@PathVariable("cardId") int cardId);

}
