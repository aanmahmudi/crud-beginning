package com.Learn.Crud.Mapper;

import org.springframework.stereotype.Component;

import com.Learn.Crud.DTO.ProductDTO;
import com.Learn.Crud.model.Product;

@Component
public class ProductMapper {
	
	public ProductDTO toDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantity(product.getQuantity());
		return productDTO;
	}
	
	public Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		return product;
		
	}

}
