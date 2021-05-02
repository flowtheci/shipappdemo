package com.cgi.dentistapp.dto;

import com.cgi.dentistapp.validator.DentistConstraint;
import com.cgi.dentistapp.validator.LocalDateConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;



public class DentistVisitDTO {

    @DentistConstraint
    String dentistName;

    @Size(min = 3, max = 35)
    @NotNull
    String customerName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @LocalDateConstraint
    LocalDate visitTime;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime visitClock;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, LocalDate visitTime, LocalTime visitClock) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
        this.visitClock = visitClock;
    }

    public String getDentistName() {return dentistName;}

    public void setDentistName(String dentistName) {this.dentistName = dentistName;}

    public LocalDate getVisitTime() {return visitTime;}

    public void setVisitTime(LocalDate visitTime) {this.visitTime = visitTime;}

    public LocalTime getVisitClock() {return visitClock;}

    public void setVisitClock(LocalTime visitClock) {this.visitClock = visitClock;}

    public String getCustomerName() {return customerName;}

    public void setCustomerName(String customerName) {this.customerName = customerName;}



}
