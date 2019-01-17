package reference.domain;

public enum Rating {
    
    // rating definitions
    BAD(-5), MEDIOCRE(-3), NOT_WATCHED(0), NEUTRAL(1), FINE(3), GOOD(5) ;
    
    // attributes
    private int value ;
    
    // constructor with input
    private Rating(int value) {
        this.value = value ;
    }
    
    // return the rating's value
    public int getValue() {
        return this.value ;
    }
}
