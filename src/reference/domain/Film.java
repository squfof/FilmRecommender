package reference.domain;

public class Film {
    
    // attributes
    private String name ;
    
    // constructor with input
    public Film(String name) {
        this.name = name ;
    }
    
    // return name of the film
    public String getName() {
        return this.name ;
    }
    
    // toString method
    @Override
    public String toString() {
        return this.name ;
    }

    // hash code for films based on name
    @Override
    public int hashCode() {
        if (this.name == null) {
            return 42 ;
        }
        return this.name.hashCode() ;
    }

    // check for equality between two films
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
        final Film otherFilm = (Film) otherObject ;
        if (this.name == null || !this.name.equals(otherFilm.getName())) {
            return false ;
        }
        
        return true ;
    }
}