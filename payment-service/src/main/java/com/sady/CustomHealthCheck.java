package com.sady;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthCheck implements HealthIndicator{

	private int errorcode = 0;

	@Override
	public Health health() {
		
		if(errorcode > 3 && errorcode < 6){
			errorcode++;
			return Health.down().withDetail("Custom Error code", errorcode).build();
		}
		errorcode++;
		
		return Health.up().build();
	}	
}
