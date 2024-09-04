package se.distansakademin.happy_dogs_app.repositories;

import org.springframework.data.repository.CrudRepository;
import se.distansakademin.happy_dogs_app.models.Person;

public interface PersonRepository extends CrudRepository<Person, String> { }
