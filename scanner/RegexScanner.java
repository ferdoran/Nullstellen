package scanner;

import java.util.ArrayList;

public class RegexScanner{
	
	public ArrayList<String> matchComponents(String function) throws IllegalArgumentException{
		RegexMatchBuilder regexTokenBuilder = new RegexMatchBuilder(function);

		// Reihenfolge aka Group Index NICHT ÄNDERN!
		regexTokenBuilder.match("(?<=([+||-]))([0-9]*[.]{0,1}[0-9]+)+$"); //Konstante am Ende
		regexTokenBuilder.match("[0-9]*[.]{0,1}[0-9]*[x]"); //Variable inklusive Konstante
		regexTokenBuilder.match("[+||-]"); //Operationzeichen
		regexTokenBuilder.match("['^'][1-9]+[0-9]*"); //Potenz (nicht negativ)
		
		if(!regexTokenBuilder.matchString().equals("")){
			throw new IllegalArgumentException("Scanner: Function contains illegal character(s)!");
		}
		return regexTokenBuilder.matches();
	}
	
}