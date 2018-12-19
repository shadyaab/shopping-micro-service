package com.sady.zuulsvr.postfilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.sady.zuulsvr.utils.FilterUtils;

@Component
public class ResponseFilter extends ZuulFilter{
	
	private static final int FILTER_ORDER = 1;
	private static final boolean SHOULD_FILTER = true;
	private Logger logger = LoggerFactory.getLogger(ResponseFilter.class);
	
	@Autowired
	private FilterUtils filterUtils;

	@Override
	public int filterOrder() {
		return FILTER_ORDER;
	}

	@Override
	public String filterType() {
		return FilterUtils.POST_FILTER_TYPE;
	}
	
	@Override
	public boolean shouldFilter() {
		return SHOULD_FILTER;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext context = RequestContext.getCurrentContext();
		
		logger.debug("Adding the correlation id to the outbound headers. {}", filterUtils.getCorrelationId());
		System.out.println("Adding the correlation id to the outbound headers: " + filterUtils.getCorrelationId());
		 
		context.getResponse().addHeader(FilterUtils.CORRELATION_ID, filterUtils.getCorrelationId());
		
		logger.debug("Completing outgoing request for {}.", context.getRequest().getRequestURI());
		System.out.println("Completing outgoing request for " + context.getRequest().getRequestURI());
		
		return null;
		
	}
	
	
	
	

}
