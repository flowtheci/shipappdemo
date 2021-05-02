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

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository dentistVisitRepository;

    public int modifyId;
    public boolean wasAvailable = true;

    // TODO pull from DB instead of hardcoding
    public List<String> dentistList = Arrays.asList("Mari Maasikas", "Juhan Juhm", "Joosep Keilast", "Urmas Hammas");

    public List<DentistVisitEntity> getAllVisits() {
        List<DentistVisitEntity> dentistVisits = new ArrayList<DentistVisitEntity>();
        dentistVisitRepository.findAll().forEach(visit -> dentistVisits.add(visit));
        return dentistVisits;
    }

    public DentistVisitEntity getVisitById(int id) {
        return dentistVisitRepository.findOne(id);
    }

    public void saveOrUpdate (DentistVisitEntity visit) {
        dentistVisitRepository.save(visit);
    }

    public void delete(int id) {
        dentistVisitRepository.delete(id);
    }

    public void modify(int id) {this.modifyId = id;}

    public boolean getAvailabilityOfLast(){
        return wasAvailable;
    }

    public DentistVisitEntity getSelectedVisit(){return dentistVisitRepository.findOne(modifyId);}

    public boolean addVisit(String dentistName, LocalDate visitTime, LocalTime visitClock, String customerName) {
        // check if visit is already taken
        List<DentistVisitEntity> dentistVisits = getAllVisits();
        DentistVisitEntity c = dentistVisits.stream()
                .filter(s -> s.getDentist().equals(dentistName) && s.getDate().equals(visitTime) && s.getTime().equals(visitClock))
                .findFirst().orElse(null);

        if (c == null)
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
