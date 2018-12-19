package com.sady.zuulsvr.routefilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sady.zuulsvr.model.AbTestingRoute;
import com.sady.zuulsvr.utils.FilterUtils;

public class SpecialRouteFilter extends ZuulFilter{

	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private Logger logger = LoggerFactory.getLogger(SpecialRouteFilter.class);
	
	@Autowired
	private FilterUtils filterUtils;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public String filterType() {
		return FilterUtils.ROUTE_FILTER_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}
	
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}
	
	@Override
	public Object run() throws ZuulException {
	
		RequestContext context = RequestContext.getCurrentContext();
		
		/*AbTestingRoute abTestingRoute = getAbRoutingInfo(filterUtils.getServiceId());*/
		
		return null;
	}
	
	private AbTestingRoute getAbRoutingInfo(String serviceName){
		ResponseEntity<AbTestingRoute> restExchange = null;
		
		try{
			restExchange = restTemplate.exchange(
								"http://payment-service/route/{serviceName}", 
								HttpMethod.GET,
								null, AbTestingRoute.class, serviceName);
		} catch (HttpClientErrorException ex ){
			if(ex.getStatusCode() == HttpStatus.NOT_FOUND){
				return null;
			} 
			throw ex;
		}
		
		return restExchange.getBody();
	}
	
	
}
