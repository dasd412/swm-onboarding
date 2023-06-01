package com.example.testcodepractice.service;

import com.example.testcodepractice.domain.Calculator;
import com.example.testcodepractice.domain.Operator;
import com.example.testcodepractice.repository.CalculatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CalculatorService {

    private final CalculatorRepository calculatorRepository;

    public CalculatorService(CalculatorRepository calculatorRepository) {
        this.calculatorRepository = calculatorRepository;
    }

    @Transactional
    public Long calculate(double operandA, double operandB, Operator operator) {

        Calculator calculator = new Calculator();

        switch (operator) {
            case ADDITION:
                calculator.add(operandA, operandB);
                break;
            case SUBTRACTION:
                calculator.subtract(operandA, operandB);
                break;
            case MULTIPLICATION:
                calculator.multiply(operandA, operandB);
                break;
            case DIVISION:
                calculator.divide(operandA, operandB);
                break;
        }
        calculatorRepository.save(calculator);
        return calculator.getId();
    }
}
