package scanner;

import java.util.ArrayList;

public class RegexScanner{
	
	//Weitere Informationen zum Ablauf http://en.wikipedia.org/wiki/Parsing
	public ArrayList<String> matchFunction(String function) throws IllegalArgumentException{
		RegexTokens regexFunction = new RegexTokens(function);

		// Reihenfolge aka Group Index NICHT ÄNDERN!
		regexFunction.match("(?<!(['^']))[0-9]+$"); //Konstante am Ende
		regexFunction.match("[0-9 || .]*[x]"); //Variable inklusive Konstante
		regexFunction.match("[+||-]"); //Operationzeichen
		regexFunction.match("['^'][1-9]+"); //Potenz (nicht negativ)
		
		if(!regexFunction.matchString().equals("")){
			throw new IllegalArgumentException("function cannot be parsed");
		}
		return regexFunction.parsedFunction();
	}
	
}