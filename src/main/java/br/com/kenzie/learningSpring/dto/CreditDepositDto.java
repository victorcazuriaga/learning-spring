package br.com.kenzie.learningSpring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record CreditDepositDto(@NotNull(message = "invalid")  @DecimalMin(value ="0.01",
        message = "Value must be higher than 0.01") float value) {


}
