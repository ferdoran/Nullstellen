import function.Function;

public class NullstellenMain {

	public static void main(String[] args) {
		Function function = null;
		try{
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
	}

}
