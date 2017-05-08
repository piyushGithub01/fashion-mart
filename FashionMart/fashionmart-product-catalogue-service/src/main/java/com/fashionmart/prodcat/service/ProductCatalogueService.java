package com.fashionmart.prodcat.service;

import java.util.List;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.common.model.product.ProductSearchCriteria;

public interface ProductCatalogueService {

	public ProductModel createProduct(ProductModel inputProductModel);
	
	public Boolean deleteProduct(ProductModel inputProductModel);
	
	public ProductModel getProductById(String id);
	
	public List<ProductModel> getProductByCriteria(ProductSearchCriteria searchCriteria);
}
