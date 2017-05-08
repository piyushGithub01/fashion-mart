package com.fashionmart.common.serviceinteface;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PricingServiceApi {

	@RequestMapping(value = "/price/get-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody double getPriceByProductId(@PathVariable String id);
}
