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
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    @Autowired
    DentistVisitRepository dentistVisitRepository;

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

    public void addVisit(String dentistName, LocalDate visitTime, LocalTime visitClock) {
        DentistVisitEntity visit = new DentistVisitEntity(dentistName, visitTime, visitClock);
        visit = dentistVisitRepository.save(visit);
    }
}
