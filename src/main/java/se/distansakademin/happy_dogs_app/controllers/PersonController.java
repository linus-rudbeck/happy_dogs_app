package se.distansakademin.happy_dogs_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.distansakademin.happy_dogs_app.DTO.PersonDTO;
import se.distansakademin.happy_dogs_app.models.Person;
import se.distansakademin.happy_dogs_app.services.PersonService;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody PersonDTO requestBody) {
        var created = service.createPerson(requestBody);

        if (created) {
            return new ResponseEntity<>("Person created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Person>> getAllPersons() {
        var persons = service.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable String id) {
        var person = service.getPersonById(id);

        if (person.isPresent()) {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable String id, @RequestBody Person person){
        var isUpdated = service.updatePerson(id, person);

        if (isUpdated) {
            return new ResponseEntity<>("Update successful", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable String id){
        var isDeleted = service.deleteById(id);

        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>("Failed to delete person", HttpStatus.NOT_FOUND);
        }
    }
}
