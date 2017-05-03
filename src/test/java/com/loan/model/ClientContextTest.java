package com.loan.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.loan.service.verification.fakeData.CreateTestData.setLoansSameIp;
import static org.junit.Assert.assertEquals;


public class ClientContextTest {

    private ClientContext client = setLoansSameIp();


    @Test
    public void getIPAddress() throws Exception {
        assertEquals(client.getIPAddress(), "192.168.1.0");
    }

    @Test
    public void setIPAddress() throws Exception {
        client.setIPAddress("0.0.0.0");
        assertEquals(client.getIPAddress(), "0.0.0.0");
    }

    @Test
    public void getFirstName() throws Exception {
        assertEquals(client.getFirstName(), "Jan");
    }

    @Test
    public void setFirstName() throws Exception {
        client.setFirstName("Andrzej");
        assertEquals(client.getFirstName(), "Andrzej");
    }

    @Test
    public void getLastName() throws Exception {
        assertEquals(client.getLastName(), "Kowalik");
    }

    @Test
    public void setLastName() throws Exception {
        client.setLastName("Nowak");
        assertEquals(client.getLastName(), ("Nowak"));
    }

    @Test
    public void getLoanList() throws Exception {
        List<Loan> loanList = client.getLoanList();
        assertEquals(4, loanList.size());
    }

    @Test
    public void setLoanList() throws Exception {
        List<Loan> list = new ArrayList<>();
        client.setLoanList(list);
        assertEquals(0, list.size());
    }


}