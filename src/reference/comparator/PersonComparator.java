package reference.comparator ;

import reference.domain.Person ;
import java.util.Comparator ;
import java.util.Map ;

public class PersonComparator implements Comparator<Person> {
    
    // attributes
    private Map<Person, Integer> peopleIdentities ;
    
    // constructor with input
    public PersonComparator(Map<Person, Integer> peopleIdentities) {
        this.peopleIdentities = peopleIdentities ;
    }

    // compare two persons
    @Override
    public int compare(Person person1, Person person2) {
        
        // descending order
        return this.peopleIdentities.get(person2) - this.peopleIdentities.get(person1) ;
    }
}
