package com.Learn.Crud.Repository.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.Learn.Crud.Repository.ProductRepository;
import com.Learn.Crud.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository{
	private final Map<Long, Product> productMap = new HashMap<>();
	private Long idCounter = 0L;
	
	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(productMap.values());
	}

	@Override
	public Optional<Product> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(productMap.get(id));
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		if (product.getId()== null) {
			product.setId(++idCounter);
		}
		productMap.put(product.getId(), product);
		return product;
	}

	@Override
	public void deleteByid(Long id) {
		// TODO Auto-generated method stub
		productMap.remove(id);
	}

}
