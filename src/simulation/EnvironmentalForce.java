package simulation;

import java.util.List;

public abstract class EnvironmentalForce {

	public EnvironmentalForce() {

	}
	public abstract void Apply(List<Mass> Masses);
}
