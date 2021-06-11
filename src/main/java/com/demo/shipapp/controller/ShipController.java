package com.demo.shipapp.controller;
import com.demo.shipapp.exceptions.ResourceNotFoundException;
import com.demo.shipapp.models.Ship;
import com.demo.shipapp.repositories.ShipRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/ships")
public class ShipController {

    private final ShipRepository shipRepository;

    public ShipController(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @GetMapping
    public List<Ship> getShips() {
        return shipRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ship getShip(@PathVariable Long id) {
        Ship ship = shipRepository.findOne(id);
        if(ship == null) {throw new ResourceNotFoundException("Ship not found");}
        return ship;
    }

    @PostMapping
    public ResponseEntity createShip(@RequestBody Ship ship) throws URISyntaxException {
        Ship savedShip = shipRepository.save(ship);
        return ResponseEntity.created(new URI("/ships/" + savedShip.getId())).body(savedShip);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateShip(@PathVariable Long id, @RequestBody Ship ship) {
        Ship currentShip = shipRepository.findOne(id);
        if (currentShip == null) {throw new ResourceNotFoundException("Ship with id " + id + " not found.");}
        currentShip.setShipType(ship.getShipType());
        currentShip.setIceClass(ship.getIceClass());
        currentShip.setFlagCountry(ship.getFlagCountry());
        currentShip.setGrossTonnage(ship.getGrossTonnage());
        currentShip = shipRepository.save(ship);

        return ResponseEntity.ok(currentShip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteShip(@PathVariable Long id) {
        shipRepository.delete(id);
        return ResponseEntity.ok().build();
    }
}