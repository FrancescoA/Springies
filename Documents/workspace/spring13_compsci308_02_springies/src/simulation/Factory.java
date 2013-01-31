package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * XXX
 * 
 * @author Robert C. Duvall
 */
public class Factory {
    // data file keywords
    private static final String MASS_KEYWORD = "mass";
    private static final String SPRING_KEYWORD = "spring";
    private static final String MUSCLE_KEYWORD = "muscle";
    private static final String GRAVITY_KEYWORD = "gravity";
    private static final String CENTERMASS_KEYWORD = "centermass";
    private static final String VISCOSITY_KEYWORD = "viscosity";
    private static final String WALLFORCE_KEYWORD = "wall";

    // mass IDs
    Map<Integer, Mass> myMasses = new HashMap<Integer, Mass>();


    /**
     * XXX.
     */
    public void loadModel (Model model, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (MASS_KEYWORD.equals(type)) {
                        model.add(massCommand(line));
                    }
                    else if (SPRING_KEYWORD.equals(type)) {
                        model.add(springCommand(line));
                    }
                    else if (MUSCLE_KEYWORD.equals(type)){
                    	model.add(muscleCommand(line));
                    	
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }
    public void loadEnvironment (Model model, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (GRAVITY_KEYWORD.equals(type)) {
                    	model.add(gravityCommand(line));
                    }
                    else if (CENTERMASS_KEYWORD.equals(type)) {
                        model.add(centermassCommand(line));
                    }
                    else if (VISCOSITY_KEYWORD.equals(type)) {
                        model.add(viscosityCommand(line));
                    }
                    else if (WALLFORCE_KEYWORD.equals(type)) {
                        model.add(wallForceCommand(line));
                    }
                }
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }

    // create mass from formatted data
    private Mass massCommand (Scanner line) {
        int id = line.nextInt();
        double x = line.nextDouble();
        double y = line.nextDouble();
        double mass = line.nextDouble();
        Mass result;
        if(mass>0){
        result = new Mass(x, y, mass);
        }
        else{
        	result = new FixedMass(x,y,mass);
        }
        myMasses.put(id,  result);
        return result;
    }
    
    

    // create spring from formatted data
    private Spring springCommand (Scanner line) {
        Mass m1 = myMasses.get(line.nextInt());
        Mass m2 = myMasses.get(line.nextInt());
        double restLength = line.nextDouble();
        double ks = line.nextDouble();
        return new Spring(m1, m2, restLength, ks);
    }
    
    private Spring muscleCommand(Scanner line) {
		Mass m1 = myMasses.get(line.nextInt());
		Mass m2 = myMasses.get(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		double amplitude = line.nextDouble();
		return new Muscle(m1, m2, restLength, ks, amplitude);
    	
	}

    
    private EnvironmentalForce gravityCommand (Scanner line) {
        double direction = line.nextDouble();
        double magnitude = line.nextDouble();
        return new Gravity(direction,magnitude);
  
    }
    
    private EnvironmentalForce centermassCommand (Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        return new CenterOfMass(magnitude,exponent);
    }
    private EnvironmentalForce viscosityCommand (Scanner line) {
        double magnitude = line.nextDouble();
        return new Viscosity(magnitude);
  
    }
    
    private EnvironmentalForce wallForceCommand (Scanner line) {
        int ID = line.nextInt();
    	double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        return new WallForce(ID,magnitude,exponent);
    }
}
