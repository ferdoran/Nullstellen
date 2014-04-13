package function;

import java.util.ArrayList;	
import java.util.Scanner;

import parser.VPObject;

public class FunctionBuilder {
	
	public String readFunctionFromStdin(){
		Scanner in = new Scanner(System.in);
		String function = in.next();
		in.close();
		return function;
	}
	
	public String buildDiffFunction(ArrayList<VPObject> vpobjects, ArrayList<String> operations){
		String diff = "";
		for(VPObject vpo : vpobjects){
			diff += operations.get(vpobjects.indexOf(vpo)) + vpo.variableDiff() + "x" + "^" + vpo.potencyDiff();
		}
		return diff;
	}

}
