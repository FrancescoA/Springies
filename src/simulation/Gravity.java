package simulation;

import java.util.List;
import java.awt.Dimension;
import util.Vector;
/**
 * XXX.
 * 
 * @author Jack Matteucci
 */

public class Gravity extends EnvironmentalForce{
    private Vector myAcceleration;
 
    /**
     * Create a gravity object with a direction and magnitude 
     */
	public Gravity(Double direction, Double magnitude) {
		super();
		myAcceleration = new Vector(direction, magnitude);
	}
	
	@Override
	/**
	 * Cycles through a list of Masses, applying the constructed gravity to each 
	 * of these forces
	 */
	public void Apply(List<Mass> Masses){
		for(Mass m : Masses){
			m.applyForce(myAcceleration);
		}
	}
	/**
	 * returns the Gravity object's acceleration constant
	 */
	public Vector getAcceleration(){
		return myAcceleration;
	}
}
