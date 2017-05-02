package com.example.model;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Client")
public class ClientContext {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "IP")
    private String IPAddress;
    private String firstName;
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    List<Loan> loanList;

    public ClientContext(String IPAddress, String firstName, String lastName, List<Loan> loanList) {
        this.IPAddress = IPAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loanList = loanList;
    }

    public ClientContext() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

}
