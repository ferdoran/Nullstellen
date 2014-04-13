import java.util.ArrayList;

import scanner.RegexScanner;
import parser.VPObject;
import parser.VPObjectManager;
import diff.DiffCalculator;
import function.FunctionBuilder;

public class NullstellenMain {

	public static void main(String[] args) {
		//Funktion einlesen
		FunctionBuilder functionBuilder = new FunctionBuilder();
		String function = functionBuilder.readFunctionFromStdin();
		
		//Funktion => Scanner
		RegexScanner scanner = new RegexScanner();
		ArrayList<String> matches = null;
		try{
			matches = scanner.matchFunction(function);
		}
		catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			System.exit(1);
		}
		finally{
			scanner = null;	
		}
		
		//Scanner matches => VPObject + Operationen
		VPObjectManager vpManager = null;
		try{
			vpManager = new VPObjectManager(matches);
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
		DiffCalculator diffCalculator = new DiffCalculator();
		for(VPObject vpo : vpobjects){
			diffCalculator.calculateDiff(vpo);
		}
		String diff = functionBuilder.buildDiffFunction(vpobjects, operations);
		
		//CleanUp
		vpobjects = null;
		operations = null;
		
		System.out.println(function);
		System.out.println(diff);
	}

}
