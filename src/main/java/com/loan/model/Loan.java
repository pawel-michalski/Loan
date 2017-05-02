package com.loan.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Loan {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date loanTo;
    private Date affected;
    private BigDecimal amount;
    // tylko na potrzeby testów. Bo cron odpalany jest co kilka sekund. A do sprawdzenia pobieram cały dzień.
    @Column(name = "checked")
    private boolean ifWasAlradyChecked;
    private String description;
    boolean risk;

    public Loan(Date loanTo, Date affected, BigDecimal amount, boolean risk) {
        this.loanTo = loanTo;
        this.affected = affected;
        this.amount = amount;
        this.risk = risk;
    }

    public Loan() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLoanTo() {
        return loanTo;
    }

    public void setLoanTo(Date loanTo) {
        this.loanTo = loanTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isRisk() {
        return risk;
    }

    public void setRisk(boolean risk) {
        this.risk = risk;
    }

    public Date getAffected() {
        return affected;
    }

    public void setAffected(Date affected) {
        this.affected = affected;
    }

    public boolean isIfWasAlradyChecked() {
        return ifWasAlradyChecked;
    }

    public void setIfWasAlradyChecked(boolean ifWasAlradyChecked) {
        this.ifWasAlradyChecked = ifWasAlradyChecked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
