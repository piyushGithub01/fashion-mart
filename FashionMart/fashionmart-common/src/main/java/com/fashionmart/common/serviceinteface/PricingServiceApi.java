package com.fashionmart.common.serviceinteface;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

public interface PricingServiceApi {

	@RequestMapping(value = "/price/get-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody double getPriceByProductId(@PathVariable String id);
	
	@RequestMapping(value = "/price/get-def-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<Double> getDefPriceByProductId(@PathVariable String id);
}
