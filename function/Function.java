package function;

import java.util.ArrayList;	
import java.util.Scanner;

import parser.VPObject;

public class Function {
	
	public String readFunctionFromStdin(){
		Scanner in = new Scanner(System.in);
		String function = in.next();
		in.close();
		return function;
	}
	
	public String derivationFunction(ArrayList<VPObject> vpobjects, ArrayList<String> operations){
		String diff = "";
		for(VPObject vpo : vpobjects){
			diff += operations.get(vpobjects.indexOf(vpo)) + vpo.variableDiff() + "x" + "^" + vpo.potencyDiff();
		}
		return diff;
	}
	
	public void calculateDerivation(VPObject vpobject){
		double variable = Double.parseDouble(vpobject.variable());
		int potency = Integer.parseInt(vpobject.potency());
		double variableDiff = variable * potency;
		int potencyDiff =  potency - 1;
		vpobject.setVariableDiff(String.valueOf(variableDiff));
		vpobject.setPotencyDiff(String.valueOf(potencyDiff));
	}
	
	public double functionValue(String function, double x){
		return 0.0; //Test
	}

}
