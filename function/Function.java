
package function;

import java.util.ArrayList;	
import java.util.Scanner;

import parser.VPObject;
import parser.VPObjectManager;
import scanner.RegexScanner;

public class Function {
	
	public static String readFunctionFromStdin(){
		Scanner in = new Scanner(System.in);
		String function = in.next();
		in.close();
		return function;
	}
	
	public static String derivation(String function){
		String derivationFunction = "";
		//Funktion => Scanner
		RegexScanner scanner = new RegexScanner();
		ArrayList<String> matches = null;
		try{
			matches = scanner.matchFunction(function);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		//Scanner matches => VPObject + Operationen
		VPObjectManager vpManager = null;
		try{
			vpManager = new VPObjectManager(matches);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		ArrayList<String> operations = vpManager.operations();
		ArrayList<VPObject> vpobjects = vpManager.vpobjects();
		
		for(VPObject vpo : vpobjects){
			Function.calculateDerivation(vpo);
			derivationFunction += operations.get(vpobjects.indexOf(vpo)) + vpo.variableDiff() + "x" + "^" + vpo.potencyDiff();
		}
		
		return derivationFunction;
	}
	
	public static void calculateDerivation(VPObject vpobject){
		double variable = Double.parseDouble(vpobject.variable());
		int potency = Integer.parseInt(vpobject.potency());
		double variableDiff = variable * potency;
		int potencyDiff =  potency - 1;
		vpobject.setVariableDiff(String.valueOf(variableDiff));
		vpobject.setPotencyDiff(String.valueOf(potencyDiff));
	}
	
	public static double functionValue(String function, double x){
		return 0.0; //Todo!
	}

}
