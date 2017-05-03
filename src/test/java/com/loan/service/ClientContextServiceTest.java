package com.loan.service;

import com.loan.model.ClientContext;
import com.loan.model.Loan;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.setLoansSameIp;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientContextServiceTest {
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
    public void deleteAll() {
        clientContextService.save(setLoansSameIp());
        clientContextService.deleteAll();
        List<ClientContext> all = clientContextService.findAll();
        assertTrue(all.size() == 0);
    }
}