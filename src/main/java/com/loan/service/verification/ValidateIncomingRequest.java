package com.loan.service.verification;


public interface ValidateIncomingRequest {

    boolean checkData(String amount,int days, String firstName,String lastName, String IP);
}
