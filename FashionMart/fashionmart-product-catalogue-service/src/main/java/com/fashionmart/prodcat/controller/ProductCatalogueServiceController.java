package com.fashionmart.prodcat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.common.model.product.ProductSearchCriteria;
import com.fashionmart.common.serviceinteface.ProductCatalogueApi;
import com.fashionmart.prodcat.service.ProductCatalogueService;

@RestController
public class ProductCatalogueServiceController implements ProductCatalogueApi{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductCatalogueServiceController.class);
	
	@Autowired
	private ProductCatalogueService productCatalogueService;
	
    @Override
	public @ResponseBody ProductModel createProduct(@RequestBody ProductModel productModel) {
		LOGGER.info("received request to create product with arguments : {}", productModel);
		return productCatalogueService.createProduct(productModel);
    }
	
    @Override
	public @ResponseBody Boolean deleteProduct(@RequestBody ProductModel productModel) {
		LOGGER.info("received request to delete product with arguments : {}", productModel);
        return productCatalogueService.deleteProduct(productModel);
    }
	
    @Override
	public @ResponseBody List<ProductModel> getProductByCriteria(@RequestBody ProductSearchCriteria searchCriteria) {
		LOGGER.info("received request to get all product by criteria : {} ", searchCriteria);
		return productCatalogueService.getProductByCriteria(searchCriteria);
    }
	
	@Override
	public @ResponseBody ProductModel getProductById(@PathVariable("id") String id) {
		LOGGER.info("received request to get product by id: {}", id);
		return productCatalogueService.getProductById(id);
	}
	
}
