package se.distansakademin.happy_dogs_app.repositories;

import org.springframework.data.repository.CrudRepository;
import se.distansakademin.happy_dogs_app.models.Dog;

public interface DogRepository extends CrudRepository<Dog, String> { }
