package com.Learn.Crud.Service.Impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Learn.Crud.DTO.ProductDTO;
import com.Learn.Crud.Mapper.ProductMapper;
import com.Learn.Crud.Repository.ProductRepository;
import com.Learn.Crud.Service.ProductService;
import com.Learn.Crud.model.Product;

@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll().stream()
				.map(productMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<ProductDTO> getProductById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id)
				.map(productMapper::toDTO);
	}

	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = productMapper.toEntity(productDTO);
		Product saveProduct = productRepository.save(product);
		return productMapper.toDTO(saveProduct);
	}

	@Override
	public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDetails) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map(product -> {
			product.setName(productDetails.getName());
			product.setPrice(productDetails.getPrice());
			
			Product updateProduct = productRepository.save(product);
			return productMapper.toDTO(updateProduct);
		});
	}

	@Override
	public boolean deleteProduct(Long id) {
		// TODO Auto-generated method stub
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteByid(id);
			return true;
		}
		return false;
		
	}

	@Override
	public Optional<ProductDTO> sellProduct(Long id, int quantity) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map(product -> {
			if (product.getQuantity()>= quantity) {
				product.setQuantity(product.getQuantity()- quantity);
				Product updateProduct = productRepository.save(product);
				return productMapper.toDTO(updateProduct);
			}else {
				throw new IllegalArgumentException("Insufficient stock to sell");
			}
		});
	}

	@Override
	public Optional<ProductDTO> restockProduct(Long id, int quantity) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).map(product -> {
			System.out.println("Restocking product ID: " + id+ "with quantity: " + quantity);
			product.setQuantity(product.getQuantity()+ quantity);
			Product updateProduct = productRepository.save(product);
			return productMapper.toDTO(updateProduct);
		});
	}

}
