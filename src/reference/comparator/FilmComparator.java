package reference.comparator ;

import reference.domain.* ;
import java.util.Comparator ;
import java.util.Map ;
import java.util.List ;

public class FilmComparator implements Comparator<Film> {
    
    // attributes
    private Map<Film, List<Rating>> ratings ;
    
    // constructor with input
    public FilmComparator(Map<Film, List<Rating>> ratings) {
        this.ratings = ratings ;
    }

    // compare two films using average rating
    @Override
    public int compare(Film film1, Film film2) {
        
        // initialize averages
        double ave1 = 0.0 ;
        double ave2 = 0.0 ;
        
        // compute average rating for each film
        for (Rating rating : this.ratings.get(film1)) {
            ave1 += rating.getValue() ;
        }
        ave1 = (double) ave1 / this.ratings.get(film1).size() ;
        
        for (Rating rating : this.ratings.get(film2)) {
            ave2 += rating.getValue() ;
        }
        ave2 = (double) ave2 / this.ratings.get(film2).size() ;
        
        // descending order
        if (ave1 > ave2) {
            return -1 ;
        }
        return 1 ;
    }   
}