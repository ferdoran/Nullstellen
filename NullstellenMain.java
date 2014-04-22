import java.util.ArrayList;

import scanner.RegexScanner;
import parser.VPObject;
import parser.ComponentsManager;
import function.Function;

public class NullstellenMain {

	public static void main(String[] args) {
		//Funktion einlesen
		Function function = new Function();
		String functionString = function.readFunctionFromStdin();
		
		//Funktion => Scanner
		RegexScanner scanner = new RegexScanner();
		ArrayList<String> matches = null;
		try{
			matches = scanner.matchFunction(functionString);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		finally{
			scanner = null;	
		}
		
		//Scanner matches => VPObject + Operationen
		ComponentsManager vpManager = null;
		try{
			vpManager = new ComponentsManager(matches);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		matches = null;
		
		//Ergebnisse sichern
		ArrayList<String> operations = vpManager.operations();
		ArrayList<VPObject> vpobjects = vpManager.vpobjects();
		
		//Ableitung erstellen
		for(VPObject vpo : vpobjects){
			function.calculateDerivation(vpo);
		}
		String diff = function.derivationFunction(vpobjects, operations);
		
		//CleanUp
		vpobjects = null;
		operations = null;
		
		System.out.println(function);
		System.out.println(diff);
	}

}
