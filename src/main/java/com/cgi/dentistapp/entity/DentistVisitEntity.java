package com.cgi.dentistapp.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Entity
@Transactional
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    public String dentist;
    @Column
    public LocalDate date;
    @Column
    public LocalTime time;



    public DentistVisitEntity() {

    }
    public DentistVisitEntity(String dentist, LocalDate date, LocalTime time) {
        this.dentist = dentist;
        this.date = date;
        this.time = time;


    }

    public int getId() {
        return id;
    }

    public void setDentist(String dentist) {this.dentist = dentist;}

    public void setDate(LocalDate date) {this.date = date;}

    public void setTime(LocalTime time) {this.time = time;}



}

;
