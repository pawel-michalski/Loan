package com.loan.controller;

import com.loan.model.ClientContext;
import com.loan.model.Loan;
import com.loan.service.ClientContextService;
import com.loan.service.LoanService;
import com.loan.service.verification.ValidateIncomingRequest;
import com.loan.tools.ErrorDto;
import com.loan.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class LoanController {

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

        if (validateIncomingRequest.checkData(amount, days, firstName, lastName, request.getRemoteAddr())) {

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

            return new ResponseEntity<>("created", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("check parameters",
                HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "loan/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLoanById(@PathVariable Long id) {
        Loan loan=null;
        try {
            loan = loanService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(loan!=null)
        return new ResponseEntity<>(loan, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDto("Loan not found"), HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "loans", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLoans() {
        List<Loan> loans=null;
        try {
            loans=loanService.findAllByRiskFalse();
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(loans!=null)
            return new ResponseEntity<>(loans, HttpStatus.OK);
        else
            return new ResponseEntity<>(new ErrorDto("Loans not found"), HttpStatus.NOT_FOUND);
    }

}
