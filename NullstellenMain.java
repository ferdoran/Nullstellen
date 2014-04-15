import function.Function;

public class NullstellenMain {

	public static void main(String[] args) {
		String f = Function.readFunctionFromStdin();
		f = Function.derivation(f);
		System.out.println(f);
	}

}
