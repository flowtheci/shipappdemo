package com.demo.shipapp.models;
import groovy.transform.EqualsAndHashCode;
import groovy.transform.ToString;

import javax.persistence.*;

@Entity
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int grossTonnage;
    private String shipType;
    private String iceClass;
    private String flagCountry;

    public Ship() {}

    public Ship(int grossTonnage, String shipType, String iceClass, String flagCountry){
        this.grossTonnage = grossTonnage;
        this.shipType = shipType;
        this.iceClass = iceClass;
        this.flagCountry = flagCountry;
    }

    public Long getId(){
        return id;
    }

    public int getGrossTonnage() {
        return grossTonnage;
    }

    public String getShipType() {
        return shipType;
    }

    public String getIceClass() {
        return iceClass;
    }

    public String getFlagCountry() {
        return flagCountry;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setGrossTonnage(int grossTonnage) {
        this.grossTonnage = grossTonnage;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public void setIceClass(String iceClass) {
        this.iceClass = iceClass;
    }

    public void setFlagCountry(String flagCountry) {
        this.flagCountry = flagCountry;
    }
}