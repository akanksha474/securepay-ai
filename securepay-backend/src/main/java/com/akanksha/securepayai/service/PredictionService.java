package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.ml.PredictionRequest;
import com.akanksha.securepayai.dto.ml.PredictionResponse;

public interface PredictionService {

    PredictionResponse predict(PredictionRequest predictionRequest);
}
