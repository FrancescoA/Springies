package src.simulation;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

import util.Location;
import view.Canvas;

public class MouseDragger {
	Mass myMass;
	Mass myMouseMass;
	Spring mySpring;
	Canvas myView;
	List<Assembly> myAssemblies;

	public MouseDragger( Canvas canvas, List<Assembly> assemblies) {
		myView = canvas;
		myAssemblies = assemblies;
		attachMasstoMouse();
	}
	
	public void update(double elapsedTime, Dimension bounds){
		myMouseMass.setCenter(new Location(myView.getMousePosition()));
		myMouseMass.update(elapsedTime, bounds);
		mySpring.update(elapsedTime, bounds);
	}
	public void paint(Graphics2D pen){
		myMouseMass.paint(pen);
		mySpring.paint(pen);
	}
	
	private void attachMasstoMouse(){
		double distance = -1;
		Mass mousepoint = new FixedMass(myView.getMousePosition().getX(),myView.getMousePosition().getY(),0);
		Mass selected = mousepoint;
		for(Assembly a : myAssemblies){
			for(Mass m : a.getMasses()){
			if(distance == -1){
				selected = m;
				distance = Math.abs(mousepoint.distance(m));
			}
			else{
				if(distance>Math.abs(mousepoint.distance(m))){
					selected = m;
				}
			}
		}
		
	}
		myMouseMass = mousepoint;
		myMass = selected;
		mySpring = new Spring(mousepoint, selected, distance, 0.1);
}

}
