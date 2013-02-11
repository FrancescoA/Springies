package src.simulation;


import util.Vector;
/**
 * XXX.
 * 
 * @author Jack Matteucci
 */

public class FixedMass extends Mass {
    /**
     * Constructs a FixedMass, which is similar to a mass, except that it cannot move.
     */
	public FixedMass(double x, double y, double mass) {
		super(x, y, mass);
	}
	  /**
     * The applyForce method on a Fixed Mass does nothing, as the mass never moves!
     */
    public void applyForce (Vector force) {
    }

}
