package com.fashionmart.prodcat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fashionmart.prodcat.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

	List<ProductEntity> findByType(String type);
	
	List<ProductEntity> findByName(String name);
	
	List<ProductEntity> findByMaker(String maker);
	
}
