package com.akanksha.securepayai.dto.ml;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PredictionRequest {
    @Positive
    @NotNull
    private Double amount;
    private Integer hour;
    private Integer accountAge;
    private Integer velocity;
}
