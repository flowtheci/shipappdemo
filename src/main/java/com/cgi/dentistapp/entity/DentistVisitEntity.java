package com.cgi.dentistapp.entity;

import org.apache.tomcat.jni.Local;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Time;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Transactional
public class DentistVisitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String dentist;
    @Column
    private LocalDate date;
    @Column
    private LocalTime time;

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


}

;
