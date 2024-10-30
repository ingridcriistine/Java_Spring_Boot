package com.example.demo.impl;

import com.example.demo.dto.ImaginaryResult;
import com.example.demo.services.ImaExpService;

public class ExampleImaginaryExponential implements ImaExpService {
    
    @Override
    public ImaginaryResult imaExp(Double A, Double b) {
        Double re = A * Math.sin(b);
        Double im = A * Math.cos(b);

        System.out.println(re);
        System.out.println(im);

        return new ImaginaryResult(re, im);
    }
}
