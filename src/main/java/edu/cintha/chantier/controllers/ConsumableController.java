package edu.cintha.chantier.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.dao.ChantierDao;
import edu.cintha.chantier.dao.ConsumableDao;
import edu.cintha.chantier.models.Chantier;
import edu.cintha.chantier.models.Consumable;
import edu.cintha.chantier.models.Operation;
import edu.cintha.chantier.views.ChantierView;
import edu.cintha.chantier.views.ConsumableView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsumableController {

    @Autowired
    ConsumableDao consumableDao;

    @GetMapping("/consumable/list")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(ConsumableView.class)
    public List<Consumable> list() {
        List<Consumable> consumableList = consumableDao.findAll(); // tab ArrayList & List = classe

        return consumableList;
    }

    @GetMapping("/consumable/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(ConsumableView.class)
    public ResponseEntity<Consumable> get(@PathVariable int id) {
        Optional<Consumable> optionalConsumable = consumableDao.findById(id);

        if (optionalConsumable.isPresent()) {
            return new ResponseEntity<>(optionalConsumable.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/consumable/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Consumable> delete(@PathVariable int id) {
        Optional<Consumable> optionalConsumable = consumableDao.findById(id);

        if (optionalConsumable.isPresent()) {
            consumableDao.deleteById(id);
            return new ResponseEntity<>(optionalConsumable.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/consumable")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(ConsumableView.class)
    public ResponseEntity<Consumable> create(@RequestBody @Valid Consumable consumable) {
        consumable.setId(null);
        consumableDao.save(consumable);

        Optional<Consumable> optionalConsumable = consumableDao.findById(consumable.getId());
        return new ResponseEntity<>(optionalConsumable.get(), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/consumable/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(ConsumableView.class)
    public ResponseEntity<Consumable> update(@RequestBody @Valid Consumable consumable, @PathVariable int id) {
        consumable.setId(id);

        Optional<Consumable> optionalConsumable = consumableDao.findById(id);

        if (optionalConsumable.isPresent()) {
            consumableDao.save(consumable);
            return new ResponseEntity<>(consumable, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/consumable-by-name/{name}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(ConsumableView.class)
    public ResponseEntity<Consumable> getByName(@PathVariable String name) {
        Optional<Consumable> optionalConsumable = consumableDao.findByName(name);

        if (optionalConsumable.isPresent()) {
            return new ResponseEntity<>(optionalConsumable.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
