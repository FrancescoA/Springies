package simulation;

import java.awt.Dimension;


public class Muscle extends Spring {
	
	public static final int PERIOD = 50;
	

	private double myAmplitude;
	private double myTime = 0.0;
	private double myOriginalLength;
	
	
	public Muscle(Mass start, Mass end, double length, double kVal, double Amplitude) {
		super(start, end, length, kVal);
		myAmplitude = Amplitude;
		myOriginalLength = super.getLength();
		
	}
	
	public void update(double elapsedTime, Dimension bounds){
		super.update(elapsedTime, bounds);
		myTime++;
		double newLength = myOriginalLength + (myAmplitude)*(Math.sin(myTime*2*Math.PI/PERIOD));
		super.setLength(newLength);
		
	}

}
