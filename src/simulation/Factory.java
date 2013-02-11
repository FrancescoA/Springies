package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import simulation.wallforce.DownForce;
import simulation.wallforce.LeftForce;
import simulation.wallforce.RightForce;
import simulation.wallforce.UpForce;



/**
 * XXX
 * 
 * @author Robert C. Duvall
 *  *      @modified by Jack Matteucci
 *  *	   @modified by Francesco Agosti
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
     * Loads a File into the model and creates all of the objects as 
     * specified in the file
     */
    public void loadAssembly (Model model, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            Assembly assembly = new Assembly();
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (MASS_KEYWORD.equals(type)) {
                        assembly.add(massCommand(line));
                    }
                    else if (SPRING_KEYWORD.equals(type)) {
                        assembly.add(springCommand(line));
                    }
                    else if (MUSCLE_KEYWORD.equals(type)){
                    	assembly.add(muscleCommand(line));
                    	
                    }
                }
            }
            model.add(assembly);
            input.close();
        }
        catch (FileNotFoundException e) {
            // should not happen because File came from user selection
            e.printStackTrace();
        }
    }
    
    /**
     * This is a method which asks for an optional data file to load in any 
     * Environmental forces the User may want to apply.  If no file is selected
     * the simulation will run with no such forces.
     */
    public void loadEnvironment (Model model, File modelFile) {
        try {
            Scanner input = new Scanner(modelFile);
            while (input.hasNext()) {
                Scanner line = new Scanner(input.nextLine());
                if (line.hasNext()) {
                    String type = line.next();
                    if (GRAVITY_KEYWORD.equals(type)) {
                    	customGravity(line);
                    }
                    else if (CENTERMASS_KEYWORD.equals(type)) {
                        customCenterOfMass(line);
                    }
                    else if (VISCOSITY_KEYWORD.equals(type)) {
                        customViscosity(line);
                    }
                    else if (WALLFORCE_KEYWORD.equals(type)) {
                        customWallForce(line);
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
        	result = new FixedMass(x,y,-mass);
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
    
    // creates muscle from the formatted data
    private Spring muscleCommand(Scanner line) {
		Mass m1 = myMasses.get(line.nextInt());
		Mass m2 = myMasses.get(line.nextInt());
		double restLength = line.nextDouble();
		double ks = line.nextDouble();
		double amplitude = line.nextDouble();
		return new Muscle(m1, m2, restLength, ks, amplitude);
    	
	}

    // creates gravity as specified by an Environment data file
    private void customGravity (Scanner line) {
        double direction = line.nextDouble();
        double magnitude = line.nextDouble();
        Gravity.getInstance(direction, magnitude);
  
    }
    // creates a CenterOfMass force as specified by an Environment data file
    private void customCenterOfMass (Scanner line) {
        double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        CenterOfMass.getInstance(magnitude, exponent);
    }
    // creates a Viscosity force as specified by an Environment data file
    private void customViscosity (Scanner line) {
        double magnitude = line.nextDouble();
        Viscosity.getInstance(magnitude);
  
    }
 
    // creates a WallForce force as specified by an Environment data file
    private void customWallForce(Scanner line) {
        int ID = line.nextInt();
    	double magnitude = line.nextDouble();
        double exponent = line.nextDouble();
        
        if(ID == 1 ){
        	DownForce.getInstance(magnitude,exponent);
        }
        else if(ID == 2){
        	LeftForce.getInstance(magnitude, exponent);
        }
        else if(ID == 3){
        	UpForce.getInstance(magnitude,exponent);
        }
        else if(ID == 4){
        	RightForce.getInstance(magnitude,exponent);
        }
    }
}
