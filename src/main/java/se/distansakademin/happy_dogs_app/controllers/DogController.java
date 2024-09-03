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
    public ResponseEntity<String> createDog(@RequestBody Dog dog){
        var created = service.createDog(dog);

        if(created){
            return new ResponseEntity<>("Dog created successfully", HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>("Failed to create dog", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Dog>> getAllDogs(){
        var dogs = service.getAllDogs();
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }
}
