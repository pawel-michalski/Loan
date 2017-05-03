package com.loan.service.verification;

import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidateIncomingRequestImplTest {
    @Autowired
    private LoanService loanService;
    @Autowired
    private ClientContextService clientContextService;
    @Autowired
    private ValidateIncomingRequest validator;
    private int maxTermDays = 30;
    private int minTermDays = 2;
    private BigDecimal maxAmount = BigDecimal.valueOf(5000.00);
    private BigDecimal minAmount = BigDecimal.valueOf(100.00);
    private String firstName = "Jan";
    private String lastName = "Kowalski";
    private String IP = "192.168.10.10";

    @Test
    public void checkData() {


        // amount less
        assertFalse("false", validator.checkData("0", 20, firstName, lastName, IP));
        // amount bigger
        assertFalse("false", validator.checkData("6000", 20, firstName, lastName, IP));
        // days less
        assertFalse("false", validator.checkData("2000", 0, firstName, lastName, IP));
        // days bigger
        assertFalse("false", validator.checkData("2000", 40, firstName, lastName, IP));
        // IP null
        assertFalse("false", validator.checkData("2000", 20, firstName, lastName, ""));
        //ok
        assertTrue(validator.checkData("2000.00", 20, firstName, lastName, IP));


    }
}