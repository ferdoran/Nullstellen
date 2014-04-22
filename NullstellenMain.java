import function.Function;
import newton.Newton;

public class NullstellenMain {

	public static void main(String[] args) {
		Function function = null;
		try{
			/*
			 * z.B. x^2+2x
			 * 		2x-1
			 * 		+3x
			 */
			function = new Function(Function.readFunctionFromStdin());
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		System.out.println(function.toString());
		System.out.println(function.value(2));
		
		Function derivation = function.derivation();
		System.out.println(derivation.toString());
		System.out.println(derivation.value(2));
		Newton newton = new Newton(function);
		System.out.println(newton.findAllRoots());
	}

}
