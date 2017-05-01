package com.example.tools;

import com.example.model.ClientContext;
import com.example.model.Loan;
import com.example.service.ClientContextService;
import com.example.service.LoanService;
import com.example.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Component
public class RunAtStart {


    private final ClientContextService repo;
    private final LoanService loanService;
    private final Logger logger = Logger.getLogger(RunAtStart.class);

    @Autowired
    public RunAtStart(ClientContextService repo, LoanService loanService) {
        this.repo = repo;
        this.loanService = loanService;
    }

    @PostConstruct
    public void runAtStart() {

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
        //loanService.save(loanList);
        ClientContext clientContext = new ClientContext();
        clientContext.setFirstName("jrobert");
        clientContext.setLastName("N5");
        clientContext.setIPAddress("1565655");
        clientContext.setLoanList(loanList);
        repo.save(clientContext);

    }


}
