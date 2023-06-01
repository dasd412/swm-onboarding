package com.example.testcodepractice.web;

import com.example.testcodepractice.domain.Operator;
import com.example.testcodepractice.service.CalculatorService;
import com.example.testcodepractice.web.dto.CalculatorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculatorController.class)//<-컨트롤러 레이어 이외의 요소는 제외해서 스프링 부트 테스트보다 가볍다.
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "/application-test.properties")
public class CalculatorControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean //<- 컨트롤러에 가짜로 주입되는 객체
    private CalculatorService calculatorService;

    @Before
    public void setup() { //컨트롤러 레이어를 테스트하기 위해 넣어야 하는 코드입니다. 그냥 복붙하세요.
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    public void add() throws Exception {
        //given
        CalculatorDTO dto = new CalculatorDTO(1, 3, Operator.ADDITION);

        when(calculatorService.calculate(1, 3, Operator.ADDITION))
                .thenReturn(4.0);

        //when and then
        MvcResult result =mockMvc.perform(MockMvcRequestBuilders.post("/calculate")// @RequestMapping url에 해당합니다.
                        .content(new ObjectMapper().writeValueAsString(dto)) //dto를 JSON으로 변환한 후, post 요청의  body로 넣습니다.
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())//200 요청이 오는 지 확인합니다.
                .andDo(print())//출력을 확인합니다.
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        assertThat(responseBody).isEqualTo("4.0"); //리스폰스 바디도 확인할 수 있습니다.
    }

    //todo 나머지 테스트 케이스도 작성해보세요
}
