package com.loan.tools;

import com.loan.model.ClientContext;
import com.loan.model.Loan;
import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import com.loan.utils.DateUtils;
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


    }


}
