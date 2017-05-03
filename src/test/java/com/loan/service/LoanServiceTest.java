package com.loan.service;

import com.loan.model.Loan;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoanServiceTest {

    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientContextService clientContextService;

    @After

    @Transactional
    public void clean() {
        clientContextService.deleteAll();
        List<Loan> all = loanService.findAll();
        all.forEach(item -> loanService.delete(item.getId()));
    }

    @Test
    public void ifAreNewAsksForLoan() throws Exception {
        loanService.save(serveLoanListOK());
        assertEquals(4, loanService.ifAreNewAsksForLoan());
    }

    @Test
    public void askForLoanThreeTimesFromOneIP() throws Exception {
        clientContextService.save(setLoansSameIp());
        List<Loan> loans = loanService.askForLoanThreeTimesFromOneIP(new Date());
        assertEquals(4, loans.size());

    }

    @Test
    public void findAllByIfWasAlradyCheckedIsFalse() throws Exception {
        loanService.save(serveLoanListOK());
        List<Loan> allByIfWasAlradyCheckedIsFalse = loanService.findAllByIfWasAlradyCheckedIsFalse();
        assertEquals(4, allByIfWasAlradyCheckedIsFalse.size());
    }

    @Test
    public void findById() throws Exception {
        Loan loan = createLoan();
        loanService.save(loan);
        Loan loanById = loanService.findById(loan.getId());
        assertEquals(loan.getAmount(), loanById.getAmount());
        assertEquals(loan.getAffected(), loanById.getAffected());
        assertEquals(loan.isRisk(), loanById.isRisk());
        assertEquals(loan.getDescription(), loanById.getDescription());
    }

    @Test
    public void findAllByRiskFalse() throws Exception {
        loanService.save(serveLoanListOK());
        List<Loan> allByRiskFalse = loanService.findAllByRiskFalse();
        assertEquals(4, allByRiskFalse.size());

    }

    @Test
    public void delete() throws Exception {
        Loan loan = createLoan();
        loanService.save(loan);
        loanService.delete(loan.getId());
        List<Loan> all = loanService.findAll();
        assertEquals(0, all.size());

    }

}