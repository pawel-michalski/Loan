package com.loan.scheduler;

import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import com.loan.service.Verification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    private static final Logger log = LoggerFactory.getLogger(Scheduler.class);

    private ClientContextService repo;
    @Autowired
    private LoanService loanService;
    @Autowired
    private Verification verification;

    @Scheduled(fixedRate = 5000)
    public void processLoans() {
        verification.verify();
    }

}
