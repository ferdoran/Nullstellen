package diff;

import parser.VPObject;

public class DiffCalculator {
	public void calculateDiff(VPObject vpobject){
		double variable = Double.parseDouble(vpobject.variable());
		int potency = Integer.parseInt(vpobject.potency());
		double variableDiff = variable * potency;
		int potencyDiff =  potency - 1;
		vpobject.setVariableDiff(String.valueOf(variableDiff));
		vpobject.setPotencyDiff(String.valueOf(potencyDiff));
	}
}
