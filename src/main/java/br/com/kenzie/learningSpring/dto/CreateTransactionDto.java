package br.com.kenzie.learningSpring.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTransactionDto(@NotNull long payer_id,
                                   @NotNull long payee_id,
                                   @NotNull float value) {
}
