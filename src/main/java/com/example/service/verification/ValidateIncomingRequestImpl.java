package com.example.service.verification;

import com.example.model.ClientContext;
import com.example.model.Loan;
import com.example.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ValidateIncomingRequestImpl implements  ValidateIncomingRequest {
    @Value("${max_term_days}")
    private int maxTermDays;
    @Value("${min_term_days}")
    private int minTermDays;
    @Value("${max_amount}")
    private BigDecimal maxAmount;
    @Value("${min_amount}")
    private BigDecimal minAmount;


    @Override
    public boolean checkData(String amount, int days, String firstName, String lastName, String IP) {
       // term is lowest or equal and days are >0, if IP is not null, amount biger then 100 and lower then 5000

        if(days<=maxTermDays&&days>minTermDays&&!IP.isEmpty()&&minAmount.compareTo(new BigDecimal(amount))==-1&&maxAmount.compareTo(new BigDecimal(amount))==1) {
          return true;
        }
        else
          return false;
    }
}
