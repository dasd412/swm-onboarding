package com.example.testcodepractice.repository;

import com.example.testcodepractice.domain.Calculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // <- 웹이나 다른 요소 없이 순수하게 jpa 쪽만 테스트해서 가볍습니다.
@TestPropertySource(locations = "/application-test.properties") // <- properties를 지정해줍니다. h2를 사용한다는 것을 지정하기 위해서입니다.
public class CalculatorRepositoryTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none(); // <- 예외 상황이 실제로 발생하는 지 확인하는 데 필요합니다.

    @Autowired
    private CalculatorRepository calculatorRepository;

    @Before // <- 테스트 케이스 시작전 각각 호출됩니다.
    public void setUp() {
        //각각의 테스트 케이스에서 발생하는 중복 코드를 이리로 옮겨서 리팩토링해주세요.
    }

    @After //<- 테스트 케이스 완료시 각각 호출됩니다.
    public void clean() {
        //이 영역에는 각 테스트 종료 후 해야 하는 일을 작성합니다. 예를 들어, db 닫기 같은 자원 회수가 있겠습니다.
        //단, 주의할 점은 application-test.properties에서 h2를 사용한다고 지정해야 합니다. 그렇지 않으면 운영에서 사용되는 db를 다 날려버릴 수 있습니다.
        calculatorRepository.deleteAll();
    }

    @Test
    public void add() {
        //given : 테스트 준비
        double operand1 = 5;
        double operand2 = 3;

        Calculator calculator = new Calculator();

        //when : 테스트 실행
        calculator.add(operand1, operand2);
        Calculator saved = calculatorRepository.save(calculator);

        //then : 테스트 검증
        assertThat(saved.getResult()).isEqualTo(8);
    }

    //todo add()와 divide 사이에 subtract(), multiply()에 관한 테스트를 만들어보세요.


    @Test
    public void divideIfDenominatorIsZero() {//분모가 0일 때의 예외 상황을 테스트합니다. 테스트 코드 이름은 최대한 풀어서 씁니다.
        //  Calculator.java의 divide() 내 checkArgument(operandB != 0, "operandB must not be zero"); 예외처리를 테스트하기 위한 코드입니다.
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("operandB must not be zero");

        double operand1 = 5;
        double operand2 = 0;

        Calculator calculator = new Calculator();

        calculator.divide(operand1,operand2);
    }

    @Test
    public void divide() {

    }
}
