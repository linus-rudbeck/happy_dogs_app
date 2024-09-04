package se.distansakademin.happy_dogs_app.DTO;


import lombok.Data;
import se.distansakademin.happy_dogs_app.models.Person;

@Data
public class PersonDTO {

    private String id;

    private String name;



    public Person toPerson(){
        return new Person(id, name, null);
    }
}
