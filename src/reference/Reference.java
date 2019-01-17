package reference ;

import reference.domain.* ;
import reference.comparator.* ;
import java.util.Collections ;
import java.util.List ;
import java.util.ArrayList ;
import java.util.Map ;

public class Reference {
    
    // attributes
    private RatingRegister register ;
    private FilmComparator  filmComparator ;
    
    // constructor with input
    public Reference(RatingRegister register) {
        this.register = register ;
        this.filmComparator = new FilmComparator(this.register.filmRatings()) ;
    }
    
    // return the film with the best average rating
    public Film bestFilmByAverage() {
        List<Film> films = new ArrayList<Film>() ;
            
        for (Film film : this.register.filmRatings().keySet()) {
            films.add(film) ;
        }
            
        if (films.isEmpty()) {
                return null ;
        }
        else {
            Collections.sort(films, this.filmComparator) ;
            return films.get(0) ;
        }
    }
    
    // return the recommender for the given person
    public Person findRecommender(Person needsAdvice) {
        
        // initialize recommender and recommendation score which must be at
        // least -5 times the number of films person has seen
        Person recommender = null ;
        int highScore = (-5) * this.register.getPersonalRatings(needsAdvice).size() ;
        
        // consider all persons that have reviewed films
        // except the person who needs advice
        for (Person person : this.register.reviewers()) {
            if (!needsAdvice.equals(person)) {
                int score = 0 ;
                
                // compute dot product of person's personal ratings "vector" 
                // with the vector of the person who needs advice
                for (Film film : this.register.getPersonalRatings(needsAdvice).keySet()) {
                    score += 
                            this.register.getRating(person, film).getValue()
                            * this.register.getRating(needsAdvice, film).getValue() ;
                }
                
                // if this person's score is greater than the current high score
                // then this is be best candidate for recommender so far
                if (score > highScore) {
                    highScore = score ;
                    recommender = person ;
                }
            }
        }
        
        // return the recommender
        return recommender ;
    }
    
    // recommend a film to the given person
    public Film recommendFilm(Person person) {
        
        // get this person's personal ratings
        Map<Film, Rating> personsRatings = this.register.getPersonalRatings(person) ;
        
        // if the person has no ratings, then return the film 
        // with the highest average rating
        if (personsRatings.isEmpty()) {
            return this.bestFilmByAverage() ; 
        }
        
        // find the person that is best suited to make a recommendation
        // if null, return null
        Person recommender = this.findRecommender(person) ;
        if (recommender == null) {
            return null ;
        }
        
        // find a film that the person hasn't seen, and that the recommender
        // rated as GOOD, or FINE if no GOOD film can be found
        int minScore = 0 ;
        Film recommendedFilm = null ;
        for (Film film : this.register.getPersonalRatings(recommender).keySet()) {
            if (!this.register.getPersonalRatings(person).containsKey(film)
                    && this.register.getPersonalRatings(recommender).get(film).getValue() > minScore) {
                recommendedFilm = film ;
                minScore = this.register.getPersonalRatings(recommender).get(film).getValue() ;
            }
        }
        
        // return the recommended film
        return recommendedFilm ;
        
        
        // original code; only looks at average rating of films
        // that haven't yet been reviewed by this person
        
        // create a list of the films that have been reviewed by anyone
        //List<Film> films = new ArrayList<Film>() ;
        //for (Film film : this.register.filmRatings().keySet()) {
        //    films.add(film) ;
        //}
        
        // delete any film that the person has already seen
        // this step will be skipped if the person hasn't reviewed any films
        //if (this.register.reviewers().contains(person)) {
        //    
        //    // create an iterator for this list of films so we can deletet films
        //    // that the person has already seen
        //    Iterator<Film> iterator = films.iterator() ;
        //    
        //    while (iterator.hasNext()) {
        //        Film film = iterator.next() ;
        //        if (this.register.getPersonalRatings(person).keySet().contains(film)) {
        //            iterator.remove() ;
        //        }
        //    }
        //}
        
        // if the list of films is now empty, return null;
        // otherwise sort the list of films using the file comparator
        // and return the first film in the list
        //if (films.isEmpty()) {
        //    return null ;
        //}
        //else {
        //    Collections.sort(films, this.filmComparator) ;
        //    return films.get(0) ;
        //}   
    }
}
