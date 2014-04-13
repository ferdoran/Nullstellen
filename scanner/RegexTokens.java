package scanner;
import java.util.regex.*;
import java.util.ArrayList;

public class RegexTokens{
	private ArrayList<String> parsedFunction = new ArrayList<>();
	private String matchString;

	public RegexTokens(String matchString){
		this.matchString = matchString;
	}
	
	public String matchString(){
		return matchString;
	}

	public ArrayList<String> parsedFunction(){
		return parsedFunction;
	}

	public void match(String regex){
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(matchString);

		while(matcher.find()) {
			int positionStart = matcher.start();
			int positionEnd = matcher.end();
			String sub = matchString.substring(positionStart,positionEnd);
			parsedFunction.add(sub);
		}
		parsedFunction.add("\0"); //Signalisierung des Ende eines Blocks

		String[] stringSplit = matchString.split(regex);
		String temp = "";
		for(String s : stringSplit){
			temp += s;
		}
		matchString = temp;
	}
}