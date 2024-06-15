package happyCoders.sealed;


/**
 * Created by krist on Jun 14, 2024.
 *
 */
public sealed class Shape permits Circle, Square, Rectangle, WeirdShape {

	// sealed, permits sind 'Contextual Keywords' somit haben diese nur
	// in einem bestimmten Kontext ihre Bedeutung
	public void sealed() {
		int permits = 5 ;
	}
}
