package src.simulation;

import java.awt.Dimension;

/**
 * Implements the muscle feature in the simulation (oscillating spring). 
 * 
 * @author Francesco Agosti
 *
 */
public class Muscle extends Spring {
	
	public static final int PERIOD = 50; // period of oscillation
	

	private double myAmplitude;
	private double myTime = 0.0;
	private double myOriginalLength; //we need original length (from the spring class) so that we can oscillate based on that length
	
	
	public Muscle(Mass start, Mass end, double length, double kVal, double Amplitude) {
		super(start, end, length, kVal);
		myAmplitude = Amplitude;
		myOriginalLength = super.getLength();
		
	}
	/**
	 * Modifies rest length of muscle based on the sine trig function. 
	 */
	public void update(double elapsedTime, Dimension bounds){
		super.update(elapsedTime, bounds);
		myTime++;
		double newLength = myOriginalLength + (myAmplitude)*(Math.sin(myTime*2*Math.PI/PERIOD));
		super.setLength(newLength);
		
	}

}
