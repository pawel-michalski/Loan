package com.loan.service.verification;

import com.loan.DemoApplication;
import com.loan.controller.LoanController;
import com.loan.model.ClientContext;
import com.loan.model.Loan;
import com.loan.service.ClientContextService;
import com.loan.service.LoanService;

import com.loan.service.verification.fakeData.CreateTestData;
import com.loan.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.*;
import static com.loan.service.verification.fakeData.CreateTestData.serveFakeLoanList;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebAppConfiguration
//@WebMvcTest(controllers = LoanController.class)
public class LoanControlerTest {

    private MockMvc mockMvc;
    @Autowired
    private LoanController loanController;
    @Autowired
    private ClientContextService contextService;
    @Autowired
    private LoanService loanService;

    private ValidateIncomingRequest validateIncomingRequest;

    public LoanControlerTest() {

    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(new LoanController()).build();
        ;

        contextService.deleteAll();



    }
    @Test
    public void testGetLoans() throws Exception{
        List<Loan> expectedLoans = serveFakeLoanList();
        when(loanService.findAll()).thenReturn(expectedLoans);
        mockMvc.perform(get("/loans")).andExpect(status().isOk());

    }


}
