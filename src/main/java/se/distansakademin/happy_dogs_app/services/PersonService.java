package se.distansakademin.happy_dogs_app.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.distansakademin.happy_dogs_app.DTO.PersonDTO;
import se.distansakademin.happy_dogs_app.models.Dog;
import se.distansakademin.happy_dogs_app.models.Person;
import se.distansakademin.happy_dogs_app.repositories.PersonRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository repository;

    public boolean createPerson(PersonDTO dto) {
        try {
            repository.save(dto.toPerson());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Iterable<Person> getAllPersons() {
        return repository.findAll();
    }

    public Optional<Person> getPersonById(String id) {
        return repository.findById(id);
    }


    public boolean updatePerson(String id, Person updatedPerson){
        var existingPerson = repository.findById(id);

        if (existingPerson.isPresent()) {
            var person = existingPerson.get();

            person.setName(updatedPerson.getName());

            repository.save(person);

            return true;
        }
        else{
            return false;
        }
    }


    public boolean deleteById(String id){
        try{
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
