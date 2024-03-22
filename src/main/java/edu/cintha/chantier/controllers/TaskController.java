package edu.cintha.chantier.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import edu.cintha.chantier.dao.TaskDao;
import edu.cintha.chantier.models.Task;
import edu.cintha.chantier.views.ChantierView;
import edu.cintha.chantier.views.TaskView;
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
public class TaskController {

    @Autowired
    TaskDao taskDao;

    @GetMapping("/task/list")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(TaskView.class)
    public List<Task> list() {
        List<Task> taskList = taskDao.findAll(); // tab ArrayList & List = classe

        return taskList;
    }

    @GetMapping("/task/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(TaskView.class)
    public ResponseEntity<Task> get(@PathVariable int id) {
        Optional<Task> optionalTask = taskDao.findById(id);

        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/task/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(TaskView.class)
    public ResponseEntity<Task> delete(@PathVariable int id) {
        Optional<Task> optionalTask = taskDao.findById(id);

        if (optionalTask.isPresent()) {
            taskDao.deleteById(id);
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/task")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(TaskView.class)
    public ResponseEntity<Task> create(@RequestBody @Valid Task task) {
        task.setId(null);
        taskDao.save(task);

        Optional<Task> optionalTask = taskDao.findById(task.getId());
        return new ResponseEntity<>(optionalTask.get(), HttpStatus.CREATED);
    }

    // Update
    @PutMapping("/task/{id}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(TaskView.class)
    public ResponseEntity<Task> update(@RequestBody @Valid Task task, @PathVariable int id) {
        task.setId(id);

        Optional<Task> optionalTask = taskDao.findById(id);

        if (optionalTask.isPresent()) {
            taskDao.save(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/task-by-name/{name}")
    @Secured({"ROLE_CHEFDECHANTIER", "ROLE_OUVRIER", "ROLE_CLIENT"})
    @JsonView(TaskView.class)
    public ResponseEntity<Task> getByName(@PathVariable String name) {
        Optional<Task> optionalTask = taskDao.findByName(name);

        if (optionalTask.isPresent()) {
            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
