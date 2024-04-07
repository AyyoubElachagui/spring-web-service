package com.example.factureservice.models;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Client {
    private UUID id;

    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String city;
}
