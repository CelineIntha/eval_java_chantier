package edu.cintha.chantier.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.dao.ChantierDao;
import edu.cintha.chantier.models.Chantier;
import edu.cintha.chantier.models.Operation;
import edu.cintha.chantier.models.Task;
import edu.cintha.chantier.views.ChantierView;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ChantierController {

    @Autowired
    ChantierDao chantierDao;

    @GetMapping("/chantier/list")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public List<Chantier> list() {
        List<Chantier> chantierList = chantierDao.findAll(); // tab ArrayList & List = classe

        return chantierList;
    }

//    @GetMapping("/chantier/{id}")
//    public ResponseEntity<Object> getChantierDetails(@PathVariable int id, @RequestParam(required = false) String temps) {
//        Optional<Chantier> optionalChantier = chantierDao.findById(id);
//        if (optionalChantier.isPresent()) {
//            Chantier chantier = optionalChantier.get();
//            if (temps != null && temps.equals("true")) {
//                long tempsTotal = calculerTempsTotalPourChantier(chantier);
//                return ResponseEntity.ok(tempsTotal);
//            } else {
//                return ResponseEntity.ok(chantier);
//            }
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    private long calculerTempsTotalPourChantier(Chantier chantier) {
//        List<Operation> operations = chantier.getOperations();
//        long tempsTotal = 0;
//        for (Operation operation : operations) {
//            Task task = operation.getTask();
//            if (task != null) {
//                tempsTotal += task.getDureeEnMinutes();
//            }
//        }
//        return tempsTotal;
//    }


    @GetMapping("/chantier/{id}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> get(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/chantier/{id}/temps")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public ResponseEntity<Long> calculerTempsTotalPourChantier(@PathVariable Integer id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);
        if (optionalChantier.isPresent()) {
            Chantier chantier = optionalChantier.get();
            List<Operation> operations = chantier.getOperations();
            long tempsTotal = 0;

            for (Operation operation : operations) {
                Task task = operation.getTask();
                if (task != null) {
                    tempsTotal += task.getDureeEnMinutes();
                }
            }

            return ResponseEntity.ok(tempsTotal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @DeleteMapping("/chantier/{id}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> delete(@PathVariable int id) {
        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.deleteById(id);
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //  Sur Thunder en POST  http://localhost:8181/chantier
    @PostMapping("/chantier")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> create(@RequestBody @Valid Chantier chantier) {
        chantier.setId(null);
        chantierDao.save(chantier);

        Optional<Chantier> optionalChantier = chantierDao.findById(chantier.getId());
        return new ResponseEntity<>(optionalChantier.get(), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/chantier/{id}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> update(@RequestBody @Valid Chantier chantier, @PathVariable int id) {
        chantier.setId(id);

        Optional<Chantier> optionalChantier = chantierDao.findById(id);

        if (optionalChantier.isPresent()) {
            chantierDao.save(chantier);
            return new ResponseEntity<>(chantier, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/chantier-by-adress/{adress}")
    @Secured("ROLE_CHEFDECHANTIER")
    @JsonView(ChantierView.class)
    public ResponseEntity<Chantier> getByNom(@PathVariable String adress) {
        Optional<Chantier> optionalChantier = chantierDao.findByAdresse(adress);

        if (optionalChantier.isPresent()) {
            return new ResponseEntity<>(optionalChantier.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
