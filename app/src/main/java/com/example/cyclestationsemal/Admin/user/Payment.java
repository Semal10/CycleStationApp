package com.example.cyclestationsemal.Admin.user;

public class Payment {

    String payId;
    String bookId;
    int paid;
    int totalPay;

    Payment(){

    }

    public Payment(String payId , String bookId , int paid , int totalPay){

        this.payId = payId;
        this.bookId = bookId;
        this.paid = paid;
        this.totalPay = totalPay;

    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(int totalPay) {
        this.totalPay = totalPay;
    }
}
