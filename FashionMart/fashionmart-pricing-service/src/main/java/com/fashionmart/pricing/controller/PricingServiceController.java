package com.fashionmart.pricing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fashionmart.common.serviceinteface.PricingServiceApi;
import com.fashionmart.pricing.service.PriceCalculatorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class PricingServiceController implements PricingServiceApi{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PricingServiceController.class);
	
	@Autowired
	private PriceCalculatorService priceCalculatorService;
	
	
	@HystrixCommand(fallbackMethod = "priceNotAvailable")
	@Override
	public @ResponseBody double getPriceByProductId(@PathVariable String id) {
		LOGGER.info("received request to get price by product id: {}", id);
		return priceCalculatorService.getPriceByProductId(id);
	}
	
	public @ResponseBody double priceNotAvailable(String id) {
		LOGGER.warn("Hystrix circuit breaker ----price not available---");
        return -1;
    }
	
}
