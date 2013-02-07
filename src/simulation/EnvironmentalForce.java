package simulation;

import java.util.List;

/**
 * XXX.
 * 
 * @author Jack Matteucci
 */

public abstract class EnvironmentalForce {
	
	public boolean mySwitch;

	public EnvironmentalForce() {
		mySwitch = false;
	}
	/**
	 * Method to be extended by all EnvironmentalForces
	 */
	public abstract void Apply(List<Mass> Masses);
	
	public void toggle(){
		mySwitch = !mySwitch;
	}
	
	public boolean getStatus(){
		return mySwitch;
	}
}
