package com.fashionmart.pricing.service;

import org.springframework.web.context.request.async.DeferredResult;

public interface PriceCalculatorService {

	public double getPriceByProductId(String id);
	
	public DeferredResult<Double> getDefPriceByProductId(String id);
	
}
