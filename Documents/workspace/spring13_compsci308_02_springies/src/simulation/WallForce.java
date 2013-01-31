package simulation;

import java.util.List;

import util.Vector;

public class WallForce extends EnvironmentalForce {

	private int myID;
	private double myMagnitude;
	private double myExponent;
	private double myAngle;

	
	public WallForce(int wallID, double magnitude, double exponent){
		super();
		myID = wallID;
		myMagnitude = magnitude;
		myExponent = exponent;
		myAngle = calculateAngle(wallID);
		
	}
	


	@Override
	public void Apply(List<Mass> Masses) {
		for(Mass m : Masses){
			double distance = calculateDistance(myID, m);
			double magnitude = myMagnitude*(Math.pow(distance, -myExponent));
			Vector force = new Vector(myAngle, magnitude);
			m.applyForce(force);
		}
		
		

	}

	private double calculateDistance(int wallID, Mass m) {
		if(wallID==1){
			return m.getY();
		}
		if(wallID==2){
			return 800 - m.getX();
		}
		if(wallID==3){
			return 600 - m.getY();
		}
		if(wallID==4){
			return m.getX();
		}
		System.out.print("invalid value");
		return 0.0;
		
	}
	
	
	private double calculateAngle(int wallID) {
		if(wallID==1){
			return 90.0;
		}
		if(wallID==2){
			return 180.0;
		}
		if(wallID==3){
			return 270.0;
		}
		if(wallID==4){
			return 0.0;
		}
		System.out.print("invalid value");
		return 0.0;
	}
}
