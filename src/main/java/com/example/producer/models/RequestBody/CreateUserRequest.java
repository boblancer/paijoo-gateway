package com.example.producer.models.RequestBody;

import lombok.Data;

@Data
public class CreateUserRequest {
    private int id;
    private String username;
    private String password;

}
