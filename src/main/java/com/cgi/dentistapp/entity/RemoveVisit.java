package com.cgi.dentistapp.entity;

// empty object for deleting an entry from the db
// probably unnecessary but it works!

public class RemoveVisit {

    public int id;
    public RemoveVisit(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}