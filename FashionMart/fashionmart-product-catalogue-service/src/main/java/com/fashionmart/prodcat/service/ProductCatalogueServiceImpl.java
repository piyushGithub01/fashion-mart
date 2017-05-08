package com.fashionmart.prodcat.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.common.model.product.ProductSearchCriteria;
import com.fashionmart.prodcat.entity.ProductEntity;
import com.fashionmart.prodcat.product.converters.ProductConverter;
import com.fashionmart.prodcat.repository.ProductRepository;

@Component
public class ProductCatalogueServiceImpl implements ProductCatalogueService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	@Transactional
	public ProductModel createProduct(ProductModel inputProductModel) {
		ProductEntity entity = ProductConverter.convertToEntity(inputProductModel);
		entity.setProductId(UUID.randomUUID().toString());
		entity.setCreatedBy("SYSTEM");
		entity.setCreatedDate(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)));
		ProductEntity savedEntity = productRepository.save(entity);
		return ProductConverter.convertToModel(savedEntity);
	}

	@Override
	@Transactional
	public Boolean deleteProduct(ProductModel inputProductModel) {
		productRepository.delete(inputProductModel.getProductId());
		return Boolean.TRUE;
	}

	@Override
	public ProductModel getProductById(String id) {
		ProductEntity item =  productRepository.findOne(id);
		if (item==null)
			return null;
		return ProductConverter.convertToModel(item);
	}

	@Override
	public List<ProductModel> getProductByCriteria(ProductSearchCriteria searchCriteria) {
		List<ProductModel> results = new ArrayList<>();
		if (searchCriteria.getType() != null) {
			List<ProductEntity> typeResult = productRepository.findByType(searchCriteria.getType());
			for(ProductEntity prod : typeResult){
				results.add(ProductConverter.convertToModel(prod));
			}
		}
		return results;
	}

}
