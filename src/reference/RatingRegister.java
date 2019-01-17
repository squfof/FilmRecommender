package reference;

import reference.domain.Film ;
import reference.domain.Person ;
import reference.domain.Rating ;
import java.util.Map ;
import java.util.HashMap ;
import java.util.List ;
import java.util.ArrayList ;

public class RatingRegister {
    
    // attributes
    private Map<Film, List<Rating>> register ;
    private Map<Person, Map<Film, Rating>> personalRatings ;
    
    // constructor without inputs
    public RatingRegister() {
        this.register = new HashMap<Film, List<Rating>>() ;
        this.personalRatings = new HashMap<Person, Map<Film, Rating>>() ;
    }
    
    // add a new rating for the given film
    public void addRating(Film film, Rating rating) {
        
        // first check to see if film is in the  film register
        if (!this.register.containsKey(film)) {
            this.register.put(film, new ArrayList<Rating>()) ;
        }
        this.register.get(film).add(rating) ;
    }
    
    // add a person's rating of a film
    public void addRating(Person person, Film film, Rating rating) {
        
        // first check to see if person is in the personal ratings register
        if (!this.personalRatings.containsKey(person)) {
            this.personalRatings.put(person, new HashMap<Film, Rating>()) ;
        }
        
        // now check that person hasn't already rated this film
        if (!this.personalRatings.get(person).containsKey(film)) {
            
            // if the person hasn't already rated this film
            // add the rating to both the personal ratings register
            // and the film register
            this.personalRatings.get(person).put(film, rating) ;
            this.addRating(film, rating) ;
        }
    }
    
    // return the list of ratings associated with the given film from the film register
    public List<Rating> getRatings(Film film) {
        
        List<Rating> ratingsForFilm = null ;
        
        // first check to see if film is in the film register
        if (this.register.containsKey(film)) {
            ratingsForFilm = this.register.get(film) ;
        }
        return ratingsForFilm ;
    }
    
    // return the given person's rating for the given film
    public Rating getRating(Person person, Film film) {
        
        // first check to see if person is in the personal ratings register
        // and has rated the film
        if (!this.personalRatings.containsKey(person)
                || !this.personalRatings.get(person).containsKey(film)) {
            return Rating.NOT_WATCHED ;
        }
        return this.personalRatings.get(person).get(film) ;
    }

    // return all of the given person's ratings
    public Map<Film, Rating> getPersonalRatings(Person person) {
        Map<Film, Rating> personsRatings = new HashMap<Film, Rating>() ;
        
        // first check to see if person is in the personal ratings register
        if (this.personalRatings.containsKey(person)) {
            personsRatings = this.personalRatings.get(person) ;
        }
        return personsRatings ;
        
    }
    
    // return all ratings from the film register
    public Map<Film, List<Rating>> filmRatings() {
        return this.register ;
    }
    
    // return the list of persons who have reviewed any film
    public List<Person> reviewers() {
        
        // initialize the list
        List<Person> personsWhoReviewed = new ArrayList<Person>() ;
        
        // add persons to the list
        for (Person person : this.personalRatings.keySet()) {
            personsWhoReviewed.add(person) ;
        }
        
        // return the list of persons
        return personsWhoReviewed ;
    }   
}