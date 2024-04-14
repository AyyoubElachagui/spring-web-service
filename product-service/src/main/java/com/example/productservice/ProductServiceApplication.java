package com.example.productservice;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository){
		return args -> {
			List<Product> products = List.of(
					Product.builder()
							.id(UUID.fromString("d00e9738-b95a-4255-88b1-5f6303e85df7"))
							.type("Type 1")
							.reference("reference 1")
							.description("description 1")
							.price(100.39)
							.build(),
					Product.builder()
							.id(UUID.fromString("b0877c8d-b031-45a0-acca-015b432f9ce1"))
							.type("Type 2")
							.reference("reference 2")
							.description("description 2")
							.price(132.39)
							.build()
			);
			productRepository.saveAll(products);
		};
	}

}
