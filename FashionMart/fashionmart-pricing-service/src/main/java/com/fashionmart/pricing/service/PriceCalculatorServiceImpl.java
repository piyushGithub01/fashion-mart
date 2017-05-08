package com.fashionmart.pricing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.pricing.feignclient.ProductCatalogueServiceFeignClient;

@Component
public class PriceCalculatorServiceImpl implements PriceCalculatorService{

	@Autowired
	private ProductCatalogueServiceFeignClient productCatalogueServiceFeignClient;
	
	@Override
	public double getPriceByProductId(String id) {
		ProductModel product = productCatalogueServiceFeignClient.getProductById(id);
		if (product != null)
			return product.getPrice();
		return -1;
	}


}
