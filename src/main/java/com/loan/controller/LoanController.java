package com.example.controller;

import com.example.model.ClientContext;
import com.example.model.Loan;
import com.example.service.ClientContextService;
import com.example.service.LoanService;
import com.example.service.verification.ValidateIncomingRequest;
import com.example.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FreshAdmin on 2017-04-29.
 */
@RestController
public class LoanController {
    private final String CHECK_PARAMETERS = "Check parameters. Amount or days";
    private final String CREATED = "Request has been added. Expected for verification";
    @Autowired
    private ClientContextService clientContextService;
    @Autowired
    private LoanService loanService;
    @Autowired
    private ValidateIncomingRequest validateIncomingRequest;

    @PostMapping(path = "askLoan")
    public ResponseEntity<?> askForLoan(HttpServletRequest request,
                                        @RequestParam String amount,
                                        @RequestParam int days,
                                        @RequestParam String firstName,
                                        @RequestParam String lastName) {

        if (validateIncomingRequest.checkData(amount,days,firstName,lastName,request.getRemoteAddr())) {

            List<Loan> lista = new ArrayList<Loan>();
            Loan tempLoan = new Loan();
            tempLoan.setAmount(new BigDecimal(amount));
            tempLoan.setAffected(new Date());
            tempLoan.setLoanTo(DateUtils.asDate(LocalDate.now().plusDays(days)));
            lista.add(tempLoan);
            ClientContext client = new ClientContext();
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setLoanList(lista);
            client.setIPAddress(request.getRemoteAddr());
            clientContextService.save(client);
            return new ResponseEntity<>(CREATED, HttpStatus.CREATED);

        }
        return new ResponseEntity<>(CHECK_PARAMETERS,
                HttpStatus.BAD_REQUEST);
    }

}
