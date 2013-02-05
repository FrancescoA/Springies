package simulation;

import java.util.List;

/**
 * XXX.
 * 
 * @author Jack Matteucci
 */

public abstract class EnvironmentalForce {

	public EnvironmentalForce() {

	}
	/**
	 * Method to be extended by all EnvironmentalForces
	 */
	public abstract void Apply(List<Mass> Masses);
}
