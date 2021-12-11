package com.example.employeecontrol;



import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Main {
    public static void main(String[] args) {
        LocalDate localDate=LocalDate.now();
        LocalDate localDate1=LocalDate.of(2021,11,10);
        LocalDate localDate2=localDate1;
        int days = (int) localDate.until(localDate1, ChronoUnit.DAYS);
        System.out.println(days);
    }
}
