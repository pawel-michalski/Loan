package com.loan.service.verification;

import com.loan.model.Loan;
import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import com.loan.service.Verification;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.*;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationImplTest {

    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientContextService clientContextService;

    @Autowired
    private Verification verification;


    @After
    @Transactional
    public void clean() {
        clientContextService.deleteAll();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> loanService.delete(item.getId()));
    }

    // added 4 asks with good parameters
    @Test
    public void verifyAllOk() throws Exception {
        loanService.save(serveLoanListOK());
        verification.verify();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> {
            assertFalse(item.isRisk());
            assertTrue(item.isIfWasAlradyChecked());
            assertNotNull(item.getDescription());
        });

    }


    // test 4 loand with time between 00:00 and 06:00
    @Test
    public void verifyWithMaxAmountAndHours() throws Exception {
        loanService.save(setMaxAmountAndTime());
        verification.verify();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> {
            assertTrue(item.isRisk());
            assertTrue(item.isIfWasAlradyChecked());
            assertNotNull(item.getDescription());
        });

    }

    // test 4 loans from the same IP
    @Test
    public void verifyWithSameIP() {
        clientContextService.save(setLoansSameIp());
        clientContextService.getOne(1L);
        verification.verify();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> {
            assertTrue(item.isRisk());
            assertTrue(item.isIfWasAlradyChecked());
            assertNotNull(item.getDescription());

        });
    }

    private void cleanAll() {
        clientContextService.deleteAll();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> loanService.delete(item.getId()));


    }


}