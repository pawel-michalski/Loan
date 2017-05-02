package com.loan.service.verification.fakeData;


import com.loan.model.ClientContext;
import com.loan.model.Loan;
import com.loan.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateTestData {

    public static ClientContext serveFakePostData(){
        List<Loan> loanList = new ArrayList<>();
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("644.9"));
        loan.setRisk(false);
        loan.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(15)));
        loan.setAffected(new Date());
        Loan loan2 = new Loan();
        loan2.setAmount(new BigDecimal("4800"));
        loan2.setRisk(false);
        loan2.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(5)));
        loan2.setAffected(new Date());
        loanList.add(loan);
        loanList.add(loan2);
        ClientContext clientContext = new ClientContext();
        clientContext.setFirstName("jrobert");
        clientContext.setLastName("N5");
        clientContext.setIPAddress("1565655");
        clientContext.setLoanList(loanList);
        return clientContext;
    }
    public static List<Loan> serveFakeLoanList(){
        List<Loan> loanList = new ArrayList<>();
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("200"));
        loan.setRisk(false);
        loan.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(15)));
        loan.setAffected(new Date());
        Loan loan2 = new Loan();
        loan2.setAmount(new BigDecimal("300"));
        loan2.setRisk(false);
        loan2.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(5)));
        loan2.setAffected(new Date());
        loanList.add(loan);
        loanList.add(loan2);
        return loanList;
    }
}
