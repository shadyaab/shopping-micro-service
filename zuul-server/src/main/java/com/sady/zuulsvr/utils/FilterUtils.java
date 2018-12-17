package com.sady.zuulsvr.utils;

import org.springframework.stereotype.Component;

import com.netflix.zuul.context.RequestContext;

@Component
public class FilterUtils {

	private static final String CORRELATION_ID = "tmx-correlation-id";
	private static final String AUTH_TOKEN = "tmx-auth-token";
	private static final String USER_ID ="tmx-user-id";
	private static final String ORG_ID = "tmx-org-id";
	public static final String PRE_FILTER_TYPE = "pre";
	public static final String POST_FILTER_TYPE = "post";
	public static final String ROUTE_FILTER_TYPE = "route";
	
	
	public String getCorrelationId(){
		RequestContext ctx = RequestContext.getCurrentContext();
		
		if(ctx.getRequest().getHeader(CORRELATION_ID) != null){
			return ctx.getRequest().getHeader(CORRELATION_ID);
		} else {
			return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
		}
	}
	
	public void setCorrelationId(String correlationId){
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
	}
	
	
	public String getOrgId(){
		RequestContext ctx = RequestContext.getCurrentContext();
		
		if(ctx.getRequest().getHeader(ORG_ID) != null){
			return ctx.getRequest().getHeader(ORG_ID);
		} else {
			return ctx.getZuulRequestHeaders().get(ORG_ID);
		}
	}
	
	public void setOrgId(String orgId){
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(ORG_ID, orgId);
	}
	
	public String getUserId(){
		RequestContext ctx = RequestContext.getCurrentContext();
		
		if(ctx.getRequest().getHeader(USER_ID) != null){
			return ctx.getRequest().getHeader(USER_ID);
		} else {
			return ctx.getZuulRequestHeaders().get(USER_ID);
		}
	}
	
	public void setUserId(String userId){
		RequestContext ctx = RequestContext.getCurrentContext();
		ctx.addZuulRequestHeader(USER_ID, userId);
	}
	
	public String getAuthToken(){
		RequestContext ctx = RequestContext.getCurrentContext();
		return ctx.getRequest().getHeader(AUTH_TOKEN);
	}
	
	public String getServiceId(){
		RequestContext ctx = RequestContext.getCurrentContext();
		
		//In static routing we might not have service id
		if(ctx.get("serviceId") == null){
			return "";
		} else {
			return ctx.get("serviceId").toString();
		}
	}
}
