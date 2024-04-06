package com.example.clientservice;

import com.example.clientservice.entity.Client;
import com.example.clientservice.repository.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ClientServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository clientRepository){
        return args -> {
            List<Client> clients = List.of(
                    Client.builder()
                            .id(UUID.randomUUID())
                            .firstName("Ayyoub 1")
                            .lastName("El Achagui 1")
                            .address("Rabat 1")
                            .phone("+212600000001")
                            .city("Rabat 1")
                            .build(),
                    Client.builder()
                            .id(UUID.randomUUID())
                            .firstName("Ayyoub 2")
                            .lastName("El Achagui 2")
                            .address("Rabat 2")
                            .phone("+212600000002")
                            .city("Rabat 2")
                            .build()
            );
            clientRepository.saveAll(clients);
        };
    }

}
