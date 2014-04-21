
package function;

import java.util.ArrayList;	
import java.util.Scanner;

import parser.VPObject;
import parser.ComponentsManager;
import scanner.RegexScanner;

public class Function {
	
	private ComponentsManager componentsManager;
	private String function;
	
	public Function(){
		throw new IllegalArgumentException("No function provieded!");
	}
	
	public Function(String function) throws IllegalArgumentException{
		this.function = function;
		parseComponents(this.function);
	}
	
	public static String readFunctionFromStdin(){
		Scanner in = new Scanner(System.in);
		String function = in.next();
		in.close();
		return function;
	}
	
	public double value(double x){
		double functionValue = 0.0;
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
		String lastOperation = componentsManager.operations().get(componentsManager.operations().size()-1);
		if(lastOperation.equals("+")){
			functionValue += Double.parseDouble(componentsManager.constants());
		}
		else{
			functionValue -= Double.parseDouble(componentsManager.constants());
		}
		return functionValue;
	}
	
	public Function derivation(){
		String derivation = "";
		
		ArrayList<String> operations = componentsManager.operations();
		ArrayList<VPObject> vpobjects = componentsManager.vpobjects();
		
		for(VPObject vpo : vpobjects){
			calculateDerivation(vpo);
			derivation += operations.get(vpobjects.indexOf(vpo)) + vpo.derivationVariable() + "x" + "^" + vpo.derivationPotency();
		}
		
		String[] splits = derivation.split("[x]+['^']+[0]");
		if(splits.length == 1){
			derivation = splits[0];
		}
		
		return new Function(derivation);
	}

	private void parseComponents(String function) throws IllegalArgumentException{
		//Funktion => Scanner
		RegexScanner scanner = new RegexScanner();
		ArrayList<String> matches = null;
		matches = scanner.matchComponents(function);
		
		//Scanner matches => VPObject + Operationen
		componentsManager = new ComponentsManager(matches);
	}
	
	private void calculateDerivation(VPObject vpobject){
		double variable = Double.parseDouble(vpobject.variable());
		int potency = Integer.parseInt(vpobject.potency());
		double variableDiff = variable * potency;
		int potencyDiff =  potency - 1;
		vpobject.setDerivationVariable(String.valueOf(variableDiff));
		vpobject.setDerivationPotency(String.valueOf(potencyDiff));
	}
}
