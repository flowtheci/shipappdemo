package com.cgi.dentistapp.entity;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;


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
    @Column
    public String customer;



    public DentistVisitEntity() {

    }
    public DentistVisitEntity(String dentist, LocalDate date, LocalTime time, String customer) {
        this.dentist = dentist;
        this.date = date;
        this.time = time;
        this.customer = customer;

    }

    public int getId() {
        return id;
    }

    public String getDentist() {return dentist;}

    public LocalDate getDate() {return date;}

    public LocalTime getTime() {return time;}

    public String getCustomer() {return customer;}

    public void setDentist(String dentist) {this.dentist = dentist;}

    public void setDate(LocalDate date) {this.date = date;}

    public void setTime(LocalTime time) {this.time = time;}

    public void setCustomer(String customer) {this.customer = customer;}





}

;
