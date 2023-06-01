package com.example.testcodepractice.web.dto;

import com.example.testcodepractice.domain.Operator;
import lombok.Getter;

@Getter
public class CalculatorDTO {

    private final double operandA;

    private final double operandB;

    private final Operator operator;

    public CalculatorDTO(double operandA, double operandB, Operator operator) {
        this.operandA = operandA;
        this.operandB = operandB;
        this.operator = operator;
    }
}
