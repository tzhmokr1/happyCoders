package happyCoders.records;


/**
 * Created by krist on Jun 13, 2024.
 *
 */
public record Point2(int x, int y) {
	
	/** Static field */ 
	private static long instanceCounter = 0;
	
	
	/** Compact constructor */
	public Point2 {
		synchronized (Point2.class) {
			instanceCounter++;
		}
		x = Math.max(x, 0);
	    y = Math.max(y, 0);
	}
	
	/** Static Method */	
	public static synchronized long getInstanceCounter() {
	    return instanceCounter;
	}

	/** Instance Method */
	public double distanceTo(Point2 p2) {
	    int dx = p2.x() - this.x();
	    int dy = p2.y() - this.y();
	    return Math.sqrt(dx * dx + dy * dy);
	}
	
	
	
	
}
