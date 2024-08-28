package com.Learn.Crud.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Learn.Crud.DTO.ProductDTO;
import com.Learn.Crud.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	public List<ProductDTO> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id){
		Optional<ProductDTO> product = productService.getProductById(id);
		return product.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {
		return productService.createProduct(productDTO);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProductDTO>updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDetails){
		Optional<ProductDTO>updatedProduct = productService.updateProduct(id, productDetails);
		return updatedProduct.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>deleteProduct(@PathVariable Long id){
		boolean isDeleted = productService.deleteProduct(id);
		if (isDeleted) {
			return ResponseEntity.noContent().build(); //204
		}else {
			return ResponseEntity.notFound().build(); //402
		}
	}
	
	@PostMapping("/{id}/sell")
	public ResponseEntity<ProductDTO>sellProduct(@PathVariable Long id, @RequestParam int quantity){
		Optional<ProductDTO>soldProduct = productService.sellProduct(id, quantity);
		return soldProduct.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/{id}/restock")
	public ResponseEntity<ProductDTO>restockProduct(@PathVariable Long id, @RequestParam int quantity){
		Optional<ProductDTO> restockedProduct = productService.restockProduct(id, quantity);
		return restockedProduct.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}

}
