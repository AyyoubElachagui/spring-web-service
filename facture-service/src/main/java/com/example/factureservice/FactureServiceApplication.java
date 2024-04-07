package com.example.factureservice;

import com.example.factureservice.client.ClientRest;
import com.example.factureservice.client.ProductRest;
import com.example.factureservice.entity.Facture;
import com.example.factureservice.entity.FactureProducts;
import com.example.factureservice.models.Client;
import com.example.factureservice.models.Product;
import com.example.factureservice.repository.FactureProductsRepository;
import com.example.factureservice.repository.FactureRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
@AllArgsConstructor
public class FactureServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactureServiceApplication.class, args);
	}

	private ClientRest clientRest;
	private ProductRest productRest;

	@Bean
	CommandLineRunner commandLineRunner(FactureRepository factureRepository, FactureProductsRepository factureProductsRepository){
		List<Client> clients = clientRest.getAllClients();
		return args -> {
			Facture facture1 = Facture.builder()
					.clientId(clients.get(0).getId())
					.client(clients.get(0))
					.amount(0)
					.build();
			Facture facture2 = Facture.builder()
					.clientId(clients.get(clients.size() - 1).getId())
					.client(clients.get(clients.size() - 1))
					.amount(0)
					.build();
			Facture factureRepositoryResponse1 = factureRepository.save(facture1);
			Facture factureRepositoryResponse2 = factureRepository.save(facture2);
			Product product1 = productRest.getProductById(UUID.fromString("304c6939-a6f1-4bad-9f13-c670943098e6"));
			FactureProducts factureProducts1 = FactureProducts.builder()
					.productId(product1.getId())
					.product(product1)
					.build();

			Product product2 = productRest.getProductById(UUID.fromString("97316dc4-ac51-4e5c-8a62-6c8b5cfd23e0"));
			FactureProducts factureProducts2 = FactureProducts.builder()
					.productId(product2.getId())
					.product(product2)
					.build();
			FactureProducts factureProductsRepositoryResponse1 = factureProductsRepository.save(factureProducts1);
			FactureProducts factureProductsRepositoryResponse2 = factureProductsRepository.save(factureProducts2);

			factureRepositoryResponse1.setOrders(List.of(factureProductsRepositoryResponse2,factureProductsRepositoryResponse1));
			factureRepositoryResponse1.setAmount(factureProductsRepositoryResponse1.getProduct().getPrice() + factureProductsRepositoryResponse2.getProduct().getPrice());

			factureRepositoryResponse2.setOrders(List.of(factureProductsRepositoryResponse2,factureProductsRepositoryResponse1));
			factureRepositoryResponse2.setAmount(factureProductsRepositoryResponse1.getProduct().getPrice() + factureProductsRepositoryResponse2.getProduct().getPrice());
			factureRepository.save(factureRepositoryResponse1);
			factureRepository.save(factureRepositoryResponse2);

		};
	}

}
