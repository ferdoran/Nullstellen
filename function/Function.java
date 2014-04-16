
package function;

import java.util.ArrayList;	
import java.util.Scanner;

import parser.VPObject;
import parser.ComponentsManager;
import scanner.RegexScanner;

public class Function {
	
	private static ComponentsManager componentsManager;
	
	public static String readFunctionFromStdin(){
		Scanner in = new Scanner(System.in);
		String function = in.next();
		in.close();
		return function;
	}
	
	public static String derivation(String function) throws IllegalArgumentException{
		String derivation = "";
		
		parseComponents(function);
		
		ArrayList<String> operations = componentsManager.operations();
		ArrayList<VPObject> vpobjects = componentsManager.vpobjects();
		
		for(VPObject vpo : vpobjects){
			Function.calculateDerivation(vpo);
			derivation += operations.get(vpobjects.indexOf(vpo)) + vpo.derivationVariable() + "x" + "^" + vpo.derivationPotency();
		}
		
		return derivation;
	}

	public static void parseComponents(String function) throws IllegalArgumentException{
		//Funktion => Scanner
		RegexScanner scanner = new RegexScanner();
		ArrayList<String> matches = null;
		matches = scanner.matchComponents(function);
		
		//Scanner matches => VPObject + Operationen
		componentsManager = new ComponentsManager(matches);
	}
	
	private static void calculateDerivation(VPObject vpobject){
		double variable = Double.parseDouble(vpobject.variable());
		int potency = Integer.parseInt(vpobject.potency());
		double variableDiff = variable * potency;
		int potencyDiff =  potency - 1;
		vpobject.setDerivationVariable(String.valueOf(variableDiff));
		vpobject.setDerivationPotency(String.valueOf(potencyDiff));
	}
	
	public static double functionValue(String function, double x) throws IllegalArgumentException{
		double functionValue = 0.0;
		if(componentsManager == null){
			throw new IllegalArgumentException("Function has yet not been parsed");
		}
		int index = 0;
		for(VPObject vpo : componentsManager.vpobjects()){
			index = componentsManager.vpobjects().indexOf(vpo);
			if(componentsManager.operations().get(index).equals("+")){
				functionValue += Double.parseDouble(vpo.variable()) * Math.pow(x, Double.parseDouble(vpo.potency()));	
			}
			else{
				functionValue -= Double.parseDouble(vpo.variable()) * Math.pow(x, Double.parseDouble(vpo.potency()));
			}
		}
		functionValue += Double.parseDouble(componentsManager.constants());
		return functionValue;
	}

}
