package happyCoders.records;


/**
 * Created by krist on Jun 13, 2024.
 *
 */
public record Point(int x, int y) {
	
	/** Default constructor */
	public Point() {
	    this(0, 0);
	}
	
	/** Canonical constructor */
	public Point(int x, int y) {
	    if (x < 0 || y < 0) throw new IllegalArgumentException();

	    this.x = x;
	    this.y = y;
	}
	
	/** Custom constructor */
	public Point(int value) {
	    this(value, value); // delegation an den kanonischen Konstruktor per this
	}
	
	
	
	  

}
