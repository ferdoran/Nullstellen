package newton;

import function.Function;

public class Newton {
	
	private Function function;
	private Function derivatedFunction;
	
	public Newton(Function f) {
		function = f;
		derivatedFunction = f.derivation();
	}
	
	public double findAllRoots() {
		//boolean isLeftPositive = function.value(-1000000) > 0;
		//boolean isRightPositive = function.value(1000000) > 0;
		
		return findOneRoot(-1000);
	}
	
	private double findOneRoot(double x0) {
		double prevX, nextX = x0;
		do {
			prevX = nextX;
			nextX = prevX - function.value(prevX) / derivatedFunction.value(prevX);
		}
		while (Math.abs(prevX-nextX) >= 0.001);
		return nextX;
	}
}