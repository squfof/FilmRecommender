package reference.domain;

public class Person {
    
    // attributes
    private String name ;
    
    // constructor with input
    public Person(String name) {
        this.name = name ;
    }
    
    // return name of the person
    public String getName() {
        return this.name ;
    }
    
    // toString method
    @Override
    public String toString() {
        return this.name ;
    }
    
    // hash code for persons based on name
    @Override
    public int hashCode() {
        if (this.name == null) {
            return 42 ;
        }
        return this.name.hashCode() ;
    }

    // check for equality between two people
    @Override
    public boolean equals(Object otherObject) {
        
        // make sure other object is not null and is of the same class
        if (otherObject == null) {
            return false ;
        }
        if (getClass() != otherObject.getClass()) {
            return false ;
        }
        
        // decide whether or not names are the same
        final Person otherPerson = (Person) otherObject ;
        if (this.name == null || !this.name.equals(otherPerson.getName())) {
            return false ;
        }
        
        return true ;
    }
}