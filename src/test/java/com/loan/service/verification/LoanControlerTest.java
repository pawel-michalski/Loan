package com.loan.service.verification;

import com.loan.controller.LoanController;
import com.loan.model.Loan;
import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.serveLoanListOK;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

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


        contextService.deleteAll();


    }

    @Test
    public void testGetLoans() throws Exception {
        List<Loan> expectedLoans = serveLoanListOK();
        when(loanService.findAll()).thenReturn(expectedLoans);
        mockMvc.perform(get("/loans")).andExpect(status().isOk());

    }


}
