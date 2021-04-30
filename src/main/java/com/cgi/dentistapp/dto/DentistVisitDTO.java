package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DentistVisitDTO {

    @Size(min = 1, max = 50)
    String dentistName;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate visitTime;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    LocalTime visitClock;
    String fullTime;


    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, LocalDate visitTime, LocalTime visitClock) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;
        this.visitClock = visitClock;



    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public LocalDate getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDate visitTime) {this.visitTime = visitTime;}

    public LocalTime getVisitClock() {return visitClock;}

    public void setVisitClock(LocalTime visitClock) {this.visitClock = visitClock;}

    public String getFullTime() {return fullTime;}


}
