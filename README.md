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
            - Default routing
        - Manual mapping of routes via service discovery
        - Manual mapping of routes using statis URLs
            - Used to route services that aren't managed by Eureka server
           
    Filters
        - Pre Filter
            - It cannot redirect to another service or endpoint
        - Post Filter
        - Route Filter
            - Used for dynamic routing
            - Used to route between two different version of same service.
            - It can dynamically route service outside zuul.
            - It doesn't redirect HTTP call, instead terminate the previous one and create the new one.
            - If it cant redirect to new route, it will redirect to orignal target service.
            
    *Correlation Id : 
        - Its is unique id that gets carried accross all the microservices on request.
        - It allows to trace the chain of events that happen through of series of microservice call.
        
        
            
            
            
                
     
    
