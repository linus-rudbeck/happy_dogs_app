package se.distansakademin.happy_dogs_app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.distansakademin.happy_dogs_app.models.Dog;
import se.distansakademin.happy_dogs_app.services.DogService;

@RestController
@RequestMapping("/api/dogs")
@RequiredArgsConstructor
public class DogController {


    private final DogService service;


    @PostMapping
    public ResponseEntity<String> createDog(@RequestBody Dog dog) {
        var created = service.createDog(dog);

        if (created) {
            return new ResponseEntity<>("Dog created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create dog", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Dog>> getAllDogs() {
        var dogs = service.getAllDogs();
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable String id) {
        var dog = service.getDogById(id);

        if (dog.isPresent()) {
            return new ResponseEntity<>(dog.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDog(@PathVariable String id, @RequestBody Dog dog){
        var isUpdated = service.updateDog(id, dog);

        if (isUpdated) {
            return new ResponseEntity<>("Dog updated", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Update failed", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDog(@PathVariable String id){
        var isDeleted = service.deleteById(id);

        if(isDeleted){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>("Failed to delete dog", HttpStatus.NOT_FOUND);
        }
    }
}
