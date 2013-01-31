package simulation;

import java.awt.Dimension;


public class Muscle extends Spring {
	
	public static final int PERIOD = 50;
	

	private double myAmplitude;
	private double myTime = 0.0;
	
	
	public Muscle(Mass start, Mass end, double length, double kVal, double Amplitude) {
		super(start, end, length, kVal);
		myAmplitude = Amplitude;
		
	}
	
	public void update(double elapsedTime, Dimension bounds){
		super.update(elapsedTime, bounds);
		myTime++;
		double displacement = (myAmplitude)*(Math.sin(myTime*2*Math.PI/PERIOD));
		super.updateLength(displacement);
		
	}

}
