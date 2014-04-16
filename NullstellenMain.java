import function.Function;

public class NullstellenMain {

	public static void main(String[] args) {
		String f = Function.readFunctionFromStdin();
		try{
			Function.parseComponents(f);
			System.out.println(Function.functionValue(f, 2));
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		f = Function.derivation(f);
		System.out.println(f);
	}

}
