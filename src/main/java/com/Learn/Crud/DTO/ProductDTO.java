package com.Learn.Crud.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private String name;
	private double price;
	private int quantity;

}
