package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.DentistVisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository dentistVisitRepository;

    public int modifyId;

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

    public DentistVisitEntity getSelectedVisit(){return dentistVisitRepository.findOne(modifyId);}

    public void addVisit(String dentistName, LocalDate visitTime, LocalTime visitClock) {
        DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTime, visitClock);
        visit = dentistVisitRepository.save(visit);
    }

    public void changeVisit(String dentistName, LocalDate visitTime, LocalTime visitClock) {
        DentistVisitEntity visit = dentistVisitRepository.findOne(modifyId);
        visit.setDentist(dentistName);
        visit.setDate(visitTime);
        visit.setTime(visitClock);
        dentistVisitRepository.save(visit);

    }

    public List<String> getDentistList() {return dentistList;}
}
