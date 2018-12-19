package com.sady.zuulsvr.prefilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sady.zuulsvr.utils.FilterUtils;

@Component
public class TrackingFilter extends ZuulFilter{
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);
	
	@Autowired
	private FilterUtils filterUtils;

	@Override
	public String filterType() {
		return FilterUtils.PRE_FILTER_TYPE;
	}
	
	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}
	
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}
	
	public boolean isCorrelationIdPresent(){
		if(filterUtils.getCorrelationId() != null){
			return true;
		}
		return false;
	}
	
	public String generateCorrelationId(){
		return java.util.UUID.randomUUID().toString();
	}
	
	@Override
	public Object run() throws ZuulException {
		
		System.out.println("Inside Pre-filter");
		
		if(isCorrelationIdPresent()){
			logger.debug("tmx-correlation-id found in tracking filter {}.", filterUtils.getCorrelationId());
			System.out.println("tmx-correlation-id found in tracking filter " +  filterUtils.getCorrelationId());
		} else {
			filterUtils.setCorrelationId(generateCorrelationId());
			logger.debug("tmx-correlation-id is generated in tracking filter {}.", filterUtils.getCorrelationId());
			System.out.println("tmx-correlation-id is generated in tracking filter " +  filterUtils.getCorrelationId());
		}
		
		RequestContext ctx = RequestContext.getCurrentContext();
		logger.debug("Processing incoming request {}.", ctx.getRequest().getRequestURI());
		System.out.println("Processing incoming request " +  ctx.getRequest().getRequestURI());

		return null;
	}

}
