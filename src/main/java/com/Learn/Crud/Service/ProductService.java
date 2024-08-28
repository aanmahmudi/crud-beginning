package com.Learn.Crud.Service;

import java.util.List;
import java.util.Optional;

import com.Learn.Crud.DTO.ProductDTO;

public interface ProductService {
	List<ProductDTO> getAllProducts();
	Optional<ProductDTO> getProductById(Long id);
	ProductDTO createProduct(ProductDTO productDTO);
	Optional<ProductDTO> updateProduct(Long id, ProductDTO productDetails);
	boolean deleteProduct(Long id);
	
	Optional<ProductDTO> sellProduct(Long id, int quantity);
	Optional<ProductDTO> restockProduct(Long id, int quantity);

}
