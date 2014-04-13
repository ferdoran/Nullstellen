package parser;

import java.util.ArrayList;

public class VPObjectManager {
	private ArrayList<String> constants = new ArrayList<>();
	private ArrayList<String> variables = new ArrayList<>();
	private ArrayList<String> potencies = new ArrayList<>();
	private ArrayList<String> operations = new ArrayList<>();
	private ArrayList<VPObject> vpobjects = new ArrayList<>();
	
	public VPObjectManager(ArrayList<String> matches) throws IllegalArgumentException{
		int groupIndex = 0;
		for(int i = 0; i < matches.size(); i++){
			if(!matches.get(i).equals("\0")){
				splitMatches(matches.get(i),groupIndex);
			}
			else{
				groupIndex++;
			}
		}
		if(variables.size() != potencies.size() || variables.size() != operations.size()){
			throw new IllegalArgumentException("function syntax is incorrect!");
		}
		for(int i = 0; i <= variables.size()-1; i++){
			vpobjects.add(buildVPObject(variables.get(i), potencies.get(i)));
		}
		cleanUp();
	}
	
	public VPObject buildVPObject(String variable, String potency) throws IllegalArgumentException{
		if(variable.length() == 1){//min. length == 2, da Konstante + x übergeben werden
			throw new IllegalArgumentException("function syntax is incorrect!");
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
		VPObject cpo = new VPObject(variableCleared,potencyCleared);
		return cpo;
	}
	
	/*Group Index: 0. Konstante am Ende
				   1. Variable inklusive Konstante
				   2. Operationzeichen
				   3. Potenz (nicht negativ)
	*/	
	public void splitMatches(String match, int groupIndex){
		switch(groupIndex){
			case 0:{
				constants.add(match);
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
	
	public void cleanUp(){
		variables = null;
		potencies = null;
		if(constants.size() > 1){
			operations.remove(operations.size()-1); //Operationszeichen mit dem die Konstante am Ende verbunden war, wird nicht mehr benötigt
			constants = null;
		}
	}
	
	public ArrayList<String> operations(){
		return operations;
	}
	
	public ArrayList<VPObject> vpobjects(){
		return vpobjects;
	}

}
