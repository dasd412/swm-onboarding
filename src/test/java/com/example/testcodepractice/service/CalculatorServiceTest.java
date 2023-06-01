package com.example.testcodepractice.service;

import com.example.testcodepractice.domain.Calculator;
import com.example.testcodepractice.domain.Operator;
import com.example.testcodepractice.repository.CalculatorRepository;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application-test.properties")
public class CalculatorServiceTest {

    @Mock//<- 주입되는 가짜 객체입니다.
    private CalculatorRepository calculatorRepository;

    @InjectMocks//<-가짜 객체가 주입되는 곳입니다.
    private CalculatorService calculatorService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void add(){
        //given : 준비
        double operand1 = 5;
        double operand2 = 3;

        Calculator mockObject=new Calculator(8);

        when(calculatorRepository.save(any(Calculator.class)))//repository가 어떤 Calculator 객체를 받더라도
                .thenReturn(mockObject);// 위의 목 오브젝트를 반환하도록 설정

        //when : 실행
        Double result=calculatorService.calculate(operand1,operand2, Operator.ADDITION);

        //then
        assertThat(result).isCloseTo(8, Offset.offset(0.005));//결괏값이 소수점 0.005이하의 정밀도로 8.0과 같은지 검증
    }
    
    //todo 나머지 테스트 케이스도 작성해보세요
}
