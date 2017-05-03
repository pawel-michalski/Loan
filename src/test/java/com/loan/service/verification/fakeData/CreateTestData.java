package com.loan.service.verification.fakeData;


import com.loan.model.ClientContext;
import com.loan.model.Loan;
import com.loan.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CreateTestData {

    public static ClientContext serveFakePostData() {
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

    public static List<Loan> serveLoanListOK() {
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
        Loan loan3 = new Loan();
        loan3.setAmount(new BigDecimal("400"));
        loan3.setRisk(false);
        loan3.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(5)));
        loan3.setAffected(new Date());
        Loan loan4 = new Loan();
        loan4.setAmount(new BigDecimal("500"));
        loan4.setRisk(false);
        loan4.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(5)));
        loan4.setAffected(new Date());
        loanList.add(loan);
        loanList.add(loan2);
        loanList.add(loan3);
        loanList.add(loan4);
        return loanList;
    }

    public static List<Loan> setMaxAmountAndTime() {
        List<Loan> listWithChangedTime = new ArrayList<>();
        serveLoanListOK().forEach(item -> {
            item.setAmount(new BigDecimal("5000"));
            item.setAffected(changeTime());
            listWithChangedTime.add(item);
        });
        listWithChangedTime.forEach(item -> {
        });
        return listWithChangedTime;
    }

    public static ClientContext setLoansSameIp() {
        ClientContext clientContext = new ClientContext();
        clientContext.setFirstName("Jan");
        clientContext.setLastName("Kowalik");
        clientContext.setIPAddress("192.168.1.0");
        clientContext.setLoanList(serveLoanListOK());
        return clientContext;
    }

    public static Loan createLoan() {
        Loan loan = new Loan();
        loan.setAffected(new Date());
        loan.setAmount(new BigDecimal("2000.00"));
        loan.setRisk(false);
        loan.setDescription("desc");
        return loan;
    }

    private static Date changeTime() {
        Random generator = new Random();
        int i = generator.nextInt(6);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, i);
        cal.set(Calendar.MINUTE, i);
        return cal.getTime();
    }
}
