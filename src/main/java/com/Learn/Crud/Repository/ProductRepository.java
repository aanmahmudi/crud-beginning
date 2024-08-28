package com.Learn.Crud.Repository;

import java.util.List;
import java.util.Optional;

import com.Learn.Crud.model.Product;

public interface ProductRepository {
	List<Product> findAll();

	Optional<Product> findById(Long id);

	Product save(Product product);

	void deleteByid(Long id);
}
