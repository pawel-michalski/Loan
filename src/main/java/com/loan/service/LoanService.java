package com.loan.service;

import com.loan.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface LoanService extends JpaRepository<Loan, Long> {

    // specjalnie te 3 zapytania są na 3 sposoby. Jako HQL, natywne oraz za pomocą domyślnego JpaRepository.
    // Po to aby Panowie zobaczyli, że wiem o co chodzi. Normalnie w kodzie bym nie robił takiego bigosu
    // bo to aż razi po oczach.....

    @Query(value = "select count(l) from Loan l where l.ifWasAlradyChecked = false")
    int ifAreNewAsksForLoan();

    // wg założeń, cron odpalany jest o północy. Bo weryfikacja ma być dla wniosków z całej doby.
    @Query(value = "select l.* from loan l join client c on l.client_id=c.id " +
            "where affected < :from  and c.ip=SELECT  c.ip  FROM loan l join client c on l.client_id=c.id" +
            " group by c.ip  having COUNT(c.ip) > 2", nativeQuery = true)
    List<Loan> askForLoanThreeTimesFromOneIP(@Param("from") Date from);


    List<Loan> findAllByIfWasAlradyCheckedIsFalse();
    Loan findById(Long id);
    List<Loan> findAllByRiskFalse();


}
