package com.example.scheduler;

import com.example.service.ClientContextService;
import com.example.service.LoanService;
import com.example.service.Verification;
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
        verification.veryfy();
    }

}
