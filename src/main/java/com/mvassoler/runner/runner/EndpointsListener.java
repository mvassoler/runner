package com.mvassoler.runner.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointsListener {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @EventListener
    public void handleContextRefresh(ApplicationReadyEvent event) {
        requestMappingHandlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
            System.out.println(requestMappingInfo + " -> " + handlerMethod);
        });
    }
}
