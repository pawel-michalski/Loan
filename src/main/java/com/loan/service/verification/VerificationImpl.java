package com.loan.service.verification;

import com.loan.model.Loan;
import com.loan.service.LoanService;
import com.loan.service.Verification;
import com.loan.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class VerificationImpl implements Verification {

    private static final Logger log = LoggerFactory.getLogger(VerificationImpl.class);

    @Autowired
    private LoanService loanService;

    @Value("${max_amount}")
    private BigDecimal max_amount;
    @Value(("${time_from}"))
    private String time_from;
    @Value(("${time_end}"))
    private String time_to;
    private static final SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");


    @Override
    public void verify() {

        // check if are new loans
        if (loanService.ifAreNewAsksForLoan() > 0) {
            log.info("Found :" + loanService.ifAreNewAsksForLoan() + " new requests for loans");
            // check if and how many asks are from same IP
                List<Loan> listWithMultipleIpRequests = loanService.askForLoanThreeTimesFromOneIP(new Date());
                  if (!listWithMultipleIpRequests.isEmpty()) {
                log.info("Found " + listWithMultipleIpRequests.size() + " loans from same IP");
                decide(listWithMultipleIpRequests,true,"multiple request same IP. Loan refused");
            }
            // check all off asks not alrady checked.
            if(!loanService.findAllByIfWasAlradyCheckedIsFalse().isEmpty()){


                List<Loan> withoutRisk=new ArrayList<>();
                List<Loan> withRisk=new ArrayList<>();

                List<Loan> listForDecide = loanService.findAllByIfWasAlradyCheckedIsFalse();
                log.info("Found " + listForDecide.size() + " to verification");
                listForDecide.forEach(item->{

                    log.info("Processing " + item.getId() + ".");
                    // max amount and hours between 00:00 and 06:00
                    if(item.getAmount().compareTo(max_amount)==0&&checkLoanRequestTime(localDateFormat, item)){
                        log.info("Refused loanId " + item.getId() + " has max amount and time between 00:00 and 06:00");
                        withRisk.add(item);
                    }
                    else{
                        log.info("Accept loanId "+item.getId());
                        withoutRisk.add(item);
                    }
                });
                    decide(withoutRisk,false,"loan acctept");
                    decide(withRisk,true,"loan not accept");
            }
        }

    }

    private boolean checkLoanRequestTime(SimpleDateFormat localDateFormat, Loan item) {
        String time = localDateFormat.format(item.getAffected());
        boolean timeBetweenTwoTime =false;
        try {
            timeBetweenTwoTime = DateUtils.isTimeBetweenTwoTime(time_from, time_to, time);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void decide(List<Loan> loanList, boolean decide, String description) {

        loanList.forEach((Loan item) -> {
            item.setDescription(description);
            item.setRisk(decide);
            item.setIfWasAlradyChecked(true);
        });
          save(loanList);
    }

    private void save(List<Loan> listToSave) {
        listToSave.forEach(item->{
            loanService.save(item);
        });
    }
}
