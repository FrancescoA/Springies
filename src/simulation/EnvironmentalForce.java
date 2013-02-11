package simulation;

import java.util.List;

import view.Canvas;

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
	 * Updates the environmental force instance if it needs to have updated values. 
	 */
	public void update(Canvas view){
		
	}
	
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
