package com.demo.shipapp.repositories;
import com.demo.shipapp.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {

}

