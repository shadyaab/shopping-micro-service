# Mechanism to interact with Ribbon
    - Spring Discovery Client (Discovery)
    - Spring Discovery Client enabled restTemplate (Rest)
    - Netflix Feign Client (Feign)
    
# Client Resiliency
    - Client side load balancing
    - Circuit breaker
    - Fallback
    - Bulkhead
    
# Api Gateway
    Avoid Single point of Failure : 
        - It should be stateless meaning no data should be stored in memory
        - Multiple instances
     
    @EnableZuulServer -> It is used when you want to create your own routing service 
        - Not used zuul inbuilt capability
        - It integrate with service discovery other than Eureka i.e. Consul.
       
    @EnableZuulProxy -> It uses inbuilt zuul capability and leverage eureka service discovery 
        - It is design by default to work with spring products.
        - It uses Eureka by default
        - It uses netflix Ribbon to do client side load balancing
        
    Zuul uses below mechanism
        - Automated mapping of routes via service discovery
        - Manual mapping of routes via service discovery
        - Manual mapping of routes using statis URLs
     
    
