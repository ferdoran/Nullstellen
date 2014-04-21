import function.Function;

public class NullstellenMain {

	public static void main(String[] args) {
		Function function = null;
		try{
			function = new Function(Function.readFunctionFromStdin());
			System.out.println(function.value(2));
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		Function f = function.derivation();
		System.out.println(f.value(2));
	}

}
