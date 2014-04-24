package bisektion;

import function.Function;


public class Bisektion {
	
	private Function function;
	private static Function derivatedFunction;
	private static Function sekante;
	private static double start;
	private static double end;
	private static double startY;
	private static double endY;
	private static double intervallMitte;   // Mitte des Intervalls
	
	public Bisektion(String s) {
		function = new Function(s);
		derivatedFunction = function.derivation();
	}
	
	public static double nullstelleSekante(double x1, double x2) {
		double nullstelle;
		double sekantenSteigung;
		double ySchnittpunkt;
		start = x1;
		end = x2;
		startY = derivatedFunction.value(start);
		endY = derivatedFunction.value(end);
		intervallMitte = start + (Math.abs(Math.abs(start) - Math.abs(end)) / 2);
		sekantenSteigung = (endY - startY) / (end - start);
		ySchnittpunkt = startY - (sekantenSteigung * start);
		sekante = new Function(sekantenSteigung + "x+"  + ySchnittpunkt);
		nullstelle = -1*(ySchnittpunkt / sekantenSteigung);
		if(nullstelle < intervallMitte && nullstelle > start)
			start = nullstelle;
		else if(nullstelle > intervallMitte && nullstelle < end)
			end = nullstelle;
		
		return nullstelle;
	}
	
	public Function getSekante() {
		return sekante;
	}
	

}
