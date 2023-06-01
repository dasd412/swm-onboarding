package com.example.testcodepractice.web;

import com.example.testcodepractice.service.CalculatorService;
import com.example.testcodepractice.web.dto.CalculatorDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping("/calculate")
    public Long calculate(@RequestBody CalculatorDTO calculatorDTO) {
        return calculatorService.calculate(calculatorDTO.getOperandA(), calculatorDTO.getOperandB(), calculatorDTO.getOperator());
    }
}
