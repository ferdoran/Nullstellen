
package function;

import java.util.ArrayList;	
import java.util.Scanner;

import parser.VPObject;
import parser.VPObjectManager;
import scanner.RegexScanner;

public class Function {
	
	private static VPObjectManager vpoManager;
	
	public static String readFunctionFromStdin(){
		Scanner in = new Scanner(System.in);
		String function = in.next();
		in.close();
		return function;
	}
	
	public static String derivation(String function) throws IllegalArgumentException{
		String derivation = "";
		
		parseComponents(function);
		
		ArrayList<String> operations = vpoManager.operations();
		ArrayList<VPObject> vpobjects = vpoManager.vpobjects();
		
		for(VPObject vpo : vpobjects){
			Function.calculateDerivation(vpo);
			derivation += operations.get(vpobjects.indexOf(vpo)) + vpo.variableDiff() + "x" + "^" + vpo.potencyDiff();
		}
		
		return derivation;
	}

	private static void parseComponents(String function) throws IllegalArgumentException{
		//Funktion => Scanner
		RegexScanner scanner = new RegexScanner();
		ArrayList<String> matches = null;
		matches = scanner.matchFunction(function);
		
		//Scanner matches => VPObject + Operationen
		vpoManager = new VPObjectManager(matches);
	}
	
	private static void calculateDerivation(VPObject vpobject){
		double variable = Double.parseDouble(vpobject.variable());
		int potency = Integer.parseInt(vpobject.potency());
		double variableDiff = variable * potency;
		int potencyDiff =  potency - 1;
		vpobject.setVariableDiff(String.valueOf(variableDiff));
		vpobject.setPotencyDiff(String.valueOf(potencyDiff));
	}
	
	public static double functionValue(String function, double x){
		double functionValue = 0.0;
		if(vpoManager == null){
			parseComponents(function);
		}
		for(VPObject vpo : vpoManager.vpobjects()){
			functionValue += Double.parseDouble(vpo.variable()) * Math.pow(x, Double.parseDouble(vpo.potency()));
		}
		functionValue += Double.parseDouble(vpoManager.constants());
		return functionValue;
	}

}
