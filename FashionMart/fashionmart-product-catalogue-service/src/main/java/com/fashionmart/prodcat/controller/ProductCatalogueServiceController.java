package com.fashionmart.prodcat.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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

	@Override
	public @ResponseBody DeferredResult<ProductModel> getDefProductById(@PathVariable("id") String id) {
		DeferredResult<ProductModel> deferredResult = new DeferredResult<>(6000l);
    	deferredResult.onTimeout(new Runnable() {
			@Override
			public void run() { // Retry on timeout
				System.out.println(id+" def res timed out "+System.currentTimeMillis());
				deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body("Request timeout occurred."));
			}
		});
    	CompletableFuture.supplyAsync(new Supplier<ProductModel>(){
			@Override
			public ProductModel get() {
				try {
					Thread.sleep(3000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return productCatalogueService.getProductById(id);
			}
    	})
        .whenCompleteAsync((result, throwable) -> {
        										System.out.println("inside complete asyn result "+result);
        										System.out.println("inside complete asyn throwable "+ throwable);
        										if(null != throwable){
        											System.out.println("throwable"+throwable.getMessage());
        											deferredResult.setErrorResult(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable.getMessage()));
        										}else{
        											deferredResult.setResult(result);
        										}
        									});
        /*.exceptionally(ex -> {deferredResult.setErrorResult(ex); 
        					System.out.println("inside exceptionally");
        					return new ArrayList<Car>();
        					});*/
    	System.out.println(id+" immediate return "+System.currentTimeMillis());
    	return deferredResult;
	}
	
}
