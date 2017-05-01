package com.example.service.verification;


import com.example.model.Loan;

public interface ValidateIncomingRequest {

    boolean checkData(String amount,int days, String firstName,String lastName, String IP);
}
