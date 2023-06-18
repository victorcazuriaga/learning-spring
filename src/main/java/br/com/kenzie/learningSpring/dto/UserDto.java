package br.com.kenzie.learningSpring.dto;

import jakarta.validation.constraints.*;

public record UserDto(@NotEmpty(message = "name cannot be null")
                      @Size(max= 92, message = "Name must be lower than 92 characters long")
                      String name,
                      @NotEmpty(message = "cpf cannot be null")
                      @Size(max = 11, message = "Cpf must be lower than 11 characters long" )
                      String cpf,
                      @NotEmpty(message = "Email cannot be null")
                      @Email
                      String email,
                      @NotEmpty
                      String password,
                      @NotEmpty
                      @Pattern(regexp = "(COMMOM|SELLER)")
                      String type) {
}
