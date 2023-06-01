package com.example.testcodepractice.domain;

import lombok.Getter;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Getter
public class Calculator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double result=0.0;

    public Calculator() {
    }

    public void add(double operandA, double operandB) {
        this.result = operandA + operandB;
    }

    public void subtract(double operandA, double operandB) {
        this.result = operandA - operandB;
    }

    public void multiply(double operandA, double operandB) {
        this.result = operandA * operandB;
    }

    public void divide(double operandA, double operandB) {
        //todo 아래 코드는 operandB==0일 경우, InvalidArgumentException을 던집니다!
        checkArgument(operandB != 0, "operandB must not be zero");
        this.result = operandA / operandB;
    }
}
