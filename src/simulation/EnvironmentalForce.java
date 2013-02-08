package simulation;

import java.util.List;

/**
 * XXX.
 * 
 * @author Jack Matteucci
 * @modified Francesco Agosti
 */

public abstract class EnvironmentalForce {
	
	private boolean mySwitch;

	public EnvironmentalForce() {
		mySwitch = false;
	}
	/**
	 * Method to be extended by all EnvironmentalForces
	 */
	public abstract void Apply(List<Mass> Masses);
	
	/**
	 * Toggles the myswitch boolean between true/false so that the model can
	 * toggle application of force. 
	 */
	public void toggle(){
		mySwitch = !mySwitch;
	}
	/**
	 * returns the value of mySwitch: a boolean that determines whether the
	 * force should be applied or not. 
	 * 
	 */
	public boolean getStatus(){
		return mySwitch;
	}


}
