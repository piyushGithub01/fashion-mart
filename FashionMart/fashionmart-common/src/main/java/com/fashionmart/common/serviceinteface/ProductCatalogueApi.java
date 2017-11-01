package com.fashionmart.common.serviceinteface;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.common.model.product.ProductSearchCriteria;

public interface ProductCatalogueApi {

	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public @ResponseBody ProductModel createProduct(@RequestBody ProductModel productModel);
	
	@RequestMapping(value = "/product/remove", method = RequestMethod.PUT)
    public @ResponseBody Boolean deleteProduct(@RequestBody ProductModel productModel);
	
	@RequestMapping(value = "/product/get-by-criteria", method = RequestMethod.PUT)
    public @ResponseBody List<ProductModel> getProductByCriteria(@RequestBody ProductSearchCriteria searchCriteria);
	
	@RequestMapping(value = "/product/get-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody ProductModel getProductById(@PathVariable("id") String id);
	
	@RequestMapping(value = "/product/get-def-by-id/{id}", method = RequestMethod.GET)
	public @ResponseBody DeferredResult<ProductModel> getDefProductById(@PathVariable("id") String id);
}
