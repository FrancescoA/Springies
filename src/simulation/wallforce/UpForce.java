package simulation.wallforce;

public class UpForce extends WallForce {
	
	private static DownForce myInstance;
	
	public UpForce(double magnitude, double exponent) {
		super(magnitude, exponent);
		
	}
}
