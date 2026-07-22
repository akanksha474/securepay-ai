package com.akanksha.securepayai.service;

import com.akanksha.securepayai.dto.ml.PredictionRequest;
import com.akanksha.securepayai.dto.ml.PredictionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PredictionServiceImpl implements PredictionService {

    @Autowired
    private RestTemplate restTemplate;

    private PredictionService predictionService;

    @Override
    public PredictionResponse predict(PredictionRequest request) {
        String url = "http://127.0.0.1:8000/predict";
        return restTemplate.postForObject(url,request,PredictionResponse.class);
    }

}
