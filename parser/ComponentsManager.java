package parser;

import java.util.ArrayList;

public class ComponentsManager {
	private String constant = "";
	private ArrayList<String> variables = new ArrayList<>();
	private ArrayList<String> potencies = new ArrayList<>();
	private ArrayList<String> operations = new ArrayList<>();
	private ArrayList<VPObject> vpobjects = new ArrayList<>();
	
	public ComponentsManager(ArrayList<String> matches) throws IllegalArgumentException{
		int groupIndex = 0;
		for(int i = 0; i < matches.size(); i++){
			if(!matches.get(i).equals("\0")){
				splitMatches(matches.get(i),groupIndex);
			}
			else{
				groupIndex++;
			}
		}
		if(constant.equals("")){
			operations.add("+");
			constant = "0";
		}
		if(variables.size() >= potencies.size()){
			int sizeDiff = variables.size() - potencies.size();
			for(int i = 0; i < sizeDiff; i++){
				potencies.add("^1");
			}
		}
		if(operations.size() < (variables.size() + 1)){ // variables.size() + 1, da variables + constant
			operations.add(0, "+");
		}
		else if(operations.size() > (variables.size() + 1)){
			throw new IllegalArgumentException("Too many operations!");
		}
		for(int i = 0; i <= variables.size()-1; i++){
			vpobjects.add(buildVPObject(variables.get(i), potencies.get(i)));
		}
		cleanUp();
	}

	private void cleanUp() {
		variables = null;
		potencies = null;
	}
	
	private VPObject buildVPObject(String variable, String potency){
		if(variable.length() == 1){
			variable = "1"+variable;
		}
		String variableArray[] = variable.split("[x]");
		String variableCleared = "";
		for (String temp : variableArray){
			variableCleared += temp;
		}
		
		String potencyArray[] = potency.split("['^']");
		String potencyCleared = "";
		for (String temp : potencyArray){
			potencyCleared += temp;
		}
		VPObject vpo = new VPObject(variableCleared,potencyCleared);
		return vpo;
	}
	
	/*Group Index: 0. Konstante am Ende
				   1. Variable inklusive Konstante
				   2. Operationzeichen
				   3. Potenz (nicht negativ)
	*/	
	private void splitMatches(String match, int groupIndex){
		switch(groupIndex){
			case 0:{
				constant = match;
				break;
			}
			case 1:{
				variables.add(match);
				break;
			}
			case 2:{
				operations.add(match);
				break;
			}
			case 3:{
				potencies.add(match);
				break;
			}
		}
	}
	
	public String constants() {
		return constant;
	}
	
	public ArrayList<String> operations(){
		return operations;
	}
	
	public ArrayList<VPObject> vpobjects(){
		return vpobjects;
	}

}
