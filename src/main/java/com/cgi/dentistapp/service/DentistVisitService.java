package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository dentistVisitRepository;

    public int modifyId;
    public boolean wasAvailable = true;
    public boolean isSearching = false;
    public String searchTerm;

    // TODO pull from DB instead of hardcoding
    public List<String> dentistList = Arrays.asList("Mari Maasikas", "Juhan Juhm", "Joosep Keilast", "Urmas Hammas");

    // Gets all visits from db, if searching then filters out everything not containing search term
    public List<DentistVisitEntity> getAllVisits() {
        List<DentistVisitEntity> dentistVisits = new ArrayList<DentistVisitEntity>();
        dentistVisitRepository.findAll().forEach(visit -> dentistVisits.add(visit));
        if(!isSearching) {
            return dentistVisits;
        }
        List<DentistVisitEntity> searchedVisits = dentistVisits.stream().filter(s -> s.getCustomer().toLowerCase().contains(searchTerm.toLowerCase())).collect(Collectors.toList());
        return searchedVisits;
    }

    public DentistVisitEntity getVisitById(int id) {
        return dentistVisitRepository.findOne(id);
    }

    public void delete(int id) {
        dentistVisitRepository.delete(id);
    }

    public void modify(int id) {this.modifyId = id;}

    public boolean getAvailabilityOfLast(){
        return wasAvailable;
    }

    public DentistVisitEntity getSelectedVisit(){return dentistVisitRepository.findOne(modifyId);}

    public void searchForVisit(String searchTerm) {
        isSearching = true;
        this.searchTerm = searchTerm;
    }

    public void resetSearch() {isSearching = false;}

    public boolean getIsSearching() {return isSearching;}

    public boolean addVisit(String dentistName, LocalDate visitTime, LocalTime visitClock, String customerName) {
        List<DentistVisitEntity> dentistVisits = getAllVisits();
        // Checks if a matching entry with the same dentist name, date and time exists
        DentistVisitEntity c = dentistVisits.stream()
                .filter(s -> s.getDentist().equals(dentistName) && s.getDate().equals(visitTime) && s.getTime().equals(visitClock))
                .findFirst().orElse(null);

        if (c == null) // If no match found, creates new DentistVisitEntity and saves to db
        {
            DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTime, visitClock, customerName);
            visit = dentistVisitRepository.save(visit);
            wasAvailable = true;
            return true;
        }
        else
        {
            wasAvailable = false;
            return false;
        }
    }

    // Modifies existing DentistVisitEntity
    public void changeVisit(String dentistName, LocalDate visitTime, LocalTime visitClock, String customerName) {
        DentistVisitEntity visit = dentistVisitRepository.findOne(modifyId);
        visit.setDentist(dentistName);
        visit.setDate(visitTime);
        visit.setTime(visitClock);
        visit.setCustomer(customerName);
        dentistVisitRepository.save(visit);

    }

    public List<String> getDentistList() {return dentistList;}

}
