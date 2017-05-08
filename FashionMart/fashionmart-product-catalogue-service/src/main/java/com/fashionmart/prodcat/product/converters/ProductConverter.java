package com.fashionmart.prodcat.product.converters;

import com.fashionmart.common.model.product.ProductModel;
import com.fashionmart.prodcat.entity.ProductEntity;

public class ProductConverter {
	
	public static ProductModel convertToModel (ProductEntity entity) {
		return ProductModel.Builder.newBuilder()
					.setCreatedBy(entity.getCreatedBy())
					.setCreatedDate(entity.getCreatedDate())
					.setMaker(entity.getMaker())
					.setName(entity.getName())
					.setPrice(entity.getPrice())
					.setProductId(entity.getProductId())
					.setQuantity(entity.getQuantity())
					.setType(entity.getType())
					.setUpdatedBy(entity.getUpdatedBy())
					.setUpdatedDate(entity.getUpdatedDate())
					.setVersion(entity.getVersion())
					.build();
	}
	
	public static ProductEntity convertToEntity (ProductModel model) {
		ProductEntity entity = new ProductEntity(
					model.getProductId(),
					model.getName(),
					model.getType(),
					model.getMaker(),
					model.getQuantity(),
					model.getPrice(),
					model.getCreatedBy(),
					model.getCreatedDate(),
					model.getUpdatedBy(),
					model.getUpdatedDate(),
					model.getVersion()
				);
		return entity;
	}

}
