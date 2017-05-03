package com.loan.service.verification;

import com.loan.controller.LoanController;
import com.loan.model.ClientContext;
import com.loan.model.Loan;
import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.View;

import javax.transaction.Transactional;
import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.createLoan;
import static com.loan.service.verification.fakeData.CreateTestData.serveFakePostData;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)


@SpringBootTest
public class LoanControlerTest {

    @Mock
    View mockView;
    private MockMvc mockMvc;
    @Autowired
    private LoanController loanController;
    @Autowired
    private ClientContextService contextService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private ValidateIncomingRequest validateIncomingRequest;

    public LoanControlerTest() {

    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
        Loan loan = createLoan();
        loanService.save(loan);
    }

    @After
    @Transactional
    public void clean() {
        contextService.deleteAll();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> loanService.delete(item.getId()));
    }

    @Test
    public void getLoanByIdTest() throws Exception {

        mockMvc.perform(get("/loan/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.amount", is(2234.55)))
                .andExpect(jsonPath("$.ifWasAlradyChecked", is(false)))
                .andExpect(jsonPath("$.risk", is(false)));
    }

    @Test
    public void getLoanByIdTestNotFound() throws Exception {
        mockMvc.perform(get("/loan/{id}", 333L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getLoansTest() throws Exception {

        ResultActions andExpect = mockMvc.perform(get("/loans"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].amount", is(2234.55)))
                .andExpect(jsonPath("$[0].ifWasAlradyChecked", is(false)))
                .andExpect(jsonPath("$[0].risk", is(false)));

    }

    @Test
    public void askForLoanTestCreated() throws Exception {
        ClientContext client = serveFakePostData();

        mockMvc.perform(post("/askLoan")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("amount", "1547")
                .param("days", "15").param("firstName", "Maciej")
                .param("lastName", "Kowalik"))
                .andExpect(status().isCreated());

    }

    @Test
    public void askForLoanTestNotCreated() throws Exception {

        mockMvc.perform(post("/askLoan")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("amount", "7")
                .param("days", "0").param("firstName", "Maciej")
                .param("lastName", "Kowalik"))
                .andExpect(status().isBadRequest());

    }


}
