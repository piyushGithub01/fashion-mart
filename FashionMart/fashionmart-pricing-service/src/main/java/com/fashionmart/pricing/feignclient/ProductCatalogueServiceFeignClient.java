package com.fashionmart.pricing.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.fashionmart.common.serviceinteface.ProductCatalogueApi;

@FeignClient("fashionmart-product-catalogue-service")
public interface ProductCatalogueServiceFeignClient extends ProductCatalogueApi {

}
