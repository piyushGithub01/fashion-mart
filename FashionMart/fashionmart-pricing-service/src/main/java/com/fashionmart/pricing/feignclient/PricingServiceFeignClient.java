package com.fashionmart.pricing.feignclient;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.fashionmart.common.serviceinteface.PricingServiceApi;

@FeignClient("fashionmart-pricing-service")
public interface PricingServiceFeignClient extends PricingServiceApi {

}
