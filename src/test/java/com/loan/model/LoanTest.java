package com.loan.model;

import com.loan.utils.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static com.loan.service.verification.fakeData.CreateTestData.serveLoanListOK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LoanTest {

    private Loan loan = serveLoanListOK().get(0);

    @Test
    public void getLoanTo() throws Exception {
        assertEquals(loan.getLoanTo(), DateUtils.asDate(LocalDate.now().plusDays(15)));
    }

    @Test
    public void setLoanTo() throws Exception {
        loan.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(10)));
        assertEquals(loan.getLoanTo(), DateUtils.asDate(LocalDate.now().plusDays(10)));
    }

    @Test
    public void getAmount() throws Exception {
        assertEquals(loan.getAmount(), new BigDecimal("200.55"));
    }

    @Test
    public void setAmount() throws Exception {
        loan.setAmount(new BigDecimal("3333"));
        assertEquals(loan.getAmount(), new BigDecimal("3333"));
    }

    @Test
    public void isRisk() throws Exception {
        assertEquals(loan.isRisk(), false);
    }

    @Test
    public void setRisk() throws Exception {
        loan.setRisk(true);
        assertEquals(loan.isRisk(), true);
    }

    @Test
    public void getAffected() throws Exception {
        assertNotNull(loan.getAffected());
    }

    @Test
    public void setAffected() throws Exception {
        loan.setAffected(DateUtils.asDate(LocalDate.now().plusDays(1)));
        assertEquals(loan.getAffected(), DateUtils.asDate(LocalDate.now().plusDays(1)));
    }

    @Test
    public void isIfWasAlradyChecked() throws Exception {
        assertEquals(loan.isIfWasAlradyChecked(), false);
    }

    @Test
    public void setIfWasAlradyChecked() throws Exception {
        loan.setIfWasAlradyChecked(true);
        assertEquals(loan.isIfWasAlradyChecked(), true);
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals(loan.getDescription(), null);
    }

    @Test
    public void setDescription() throws Exception {
        loan.setDescription("opis");
        assertEquals(loan.getDescription(), "opis");
    }

}