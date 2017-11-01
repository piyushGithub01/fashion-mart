package com.fashionmart.pricing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.pricing.feignclient.ProductCatalogueServiceFeignClient;

@Component
public class PriceCalculatorServiceImpl implements PriceCalculatorService{

	@Autowired
	private ProductCatalogueServiceFeignClient productCatalogueServiceFeignClient;
	
	private AsyncRestTemplate asycTemp;
	
	@Override
	public double getPriceByProductId(String id) {
		ProductModel product = productCatalogueServiceFeignClient.getProductById(id);
		if (product != null)
			return product.getPrice();
		return -1;
	}

	@Override
	public DeferredResult<Double> getDefPriceByProductId(String id) {

		DeferredResult<Double> deferredResult = new DeferredResult<>();
		deferredResult.onTimeout(new Runnable() {
			@Override
			public void run() { // Retry on timeout
				System.out.println(id +" client def res timed out "+System.currentTimeMillis());
				deferredResult.setErrorResult(new Exception("client req time out"));
				//deferredResult.setErrorResult(new Exception("Time Out"));
			}
		});
		asycTemp = new AsyncRestTemplate();
	    ListenableFuture<ResponseEntity<ProductModel>> future = asycTemp.exchange(
	            "http://localhost:8882/product/get-def-by-id/{id}",
	            HttpMethod.GET,
	            null,
	            new ParameterizedTypeReference<ProductModel>() {
	            },
	            id);
	    future.addCallback(new SuccessCallback<ResponseEntity<ProductModel>>(){
								@Override
								public void onSuccess(ResponseEntity<ProductModel> result) {
									System.out.println(id +" in sucess of listnet future "+System.currentTimeMillis());
									deferredResult.setResult(result.getBody().getPrice());
								}
						    }, 
	    				  new FailureCallback(){
								@Override
								public void onFailure(Throwable ex) {
									System.out.println(id +" in failure of listnet future "+System.currentTimeMillis());
									System.out.println(ex.getMessage());
									deferredResult.setErrorResult(ex);
								}
							    });
		return deferredResult;
	}

}
