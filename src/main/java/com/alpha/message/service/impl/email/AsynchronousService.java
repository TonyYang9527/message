package com.alpha.message.service.impl.email;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsynchronousService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AsynchronousService.class);    
	@Async
    public void printEmployees() throws InterruptedException {
		LOGGER.info("Called AsynchronousService");
		  for(int i=1; i<10; i++){
	            TimeUnit.MICROSECONDS.sleep(1);
	            LOGGER.info("------------------");
	        }
	    
    }
}
