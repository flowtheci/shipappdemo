package com.cgi.dentistapp.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistVisitRepository extends CrudRepository<DentistVisitEntity, Integer> {
}

// Based off of https://stackabuse.com/integrating-h2-database-with-spring-boot/
//