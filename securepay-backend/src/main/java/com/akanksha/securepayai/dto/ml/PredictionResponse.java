package com.akanksha.securepayai.dto.ml;

import lombok.Data;

@Data
public class PredictionResponse {
    private Boolean fraud;
    private Double probability;
}
