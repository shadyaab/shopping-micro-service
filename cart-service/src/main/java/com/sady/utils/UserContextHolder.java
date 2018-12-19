package com.sady.utils;

public class UserContextHolder {
	
	private static final ThreadLocal<UserContext> userContextThread = new ThreadLocal<UserContext>();

	public static final UserContext getContext(){
		UserContext context = userContextThread.get();
		
		if(context == null){
			context = createEmptyContext();
			userContextThread.set(context);
		}
		
		return  userContextThread.get();
	}
	
	public static final void setContext(UserContext context){
		userContextThread.set(context);
	}
	
	public static final UserContext createEmptyContext(){
		return new UserContext();
	}
	
}
