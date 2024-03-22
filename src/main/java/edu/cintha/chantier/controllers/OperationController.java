package edu.cintha.chantier.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.dao.OperationDao;
import edu.cintha.chantier.models.Operation;
import edu.cintha.chantier.views.ChantierView;
import edu.cintha.chantier.views.OperationView;
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
public class OperationController {

    @Autowired
    OperationDao operationDao;

    @GetMapping("/operation/list")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public List<Operation> list() {
        List<Operation> operationList = operationDao.findAll(); // tab ArrayList & List = classe

        return operationList;
    }

    @GetMapping("/operation/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> get(@PathVariable int id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/operation/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER"})
    @JsonView(ChantierView.class)
    public ResponseEntity<Operation> delete(@PathVariable int id) {
        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            operationDao.deleteById(id);
            return new ResponseEntity<>(optionalOperation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/operation")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> create(@RequestBody @Valid Operation operation) {
        operation.setId(null);
        operationDao.save(operation);

        Optional<Operation> optionalOperation = operationDao.findById(operation.getId());
        return new ResponseEntity<>(optionalOperation.get(), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/operation/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER"})
    @JsonView(OperationView.class)
    public ResponseEntity<Operation> update(@RequestBody @Valid Operation operation, @PathVariable int id) {
        operation.setId(id);

        Optional<Operation> optionalOperation = operationDao.findById(id);

        if (optionalOperation.isPresent()) {
            operationDao.save(operation);
            return new ResponseEntity<>(operation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
