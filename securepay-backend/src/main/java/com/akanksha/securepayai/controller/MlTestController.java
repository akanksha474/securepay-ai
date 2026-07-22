package com.akanksha.securepayai.controller;

import com.akanksha.securepayai.dto.ml.PredictionRequest;
import com.akanksha.securepayai.dto.ml.PredictionResponse;
import com.akanksha.securepayai.service.PredictionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ml-test")
public class MlTestController {

    private final PredictionService predictionService;

    @PostMapping("/predict")
    public PredictionResponse predict(@RequestBody PredictionRequest request){

        return predictionService.predict(request);
    }
}
