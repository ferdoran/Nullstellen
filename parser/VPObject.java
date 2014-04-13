package parser;

public class VPObject {
	private String variable; //ohne x, da es für die Ableitung nicht relevant ist
	private String potency;
	private String variableDiff; //ohne x, da es für die Ableitung nicht relevant ist
	private String potencyDiff;
	
	public VPObject(String constant, String potency){
		this.variable = constant;
		this.potency = potency;
		variableDiff = "";
		potencyDiff = "";
	}
	
	public String variable(){
		return variable;
	}
	
	public String potency(){
		return potency;
	}
	
	public String variableDiff(){
		return variableDiff;
	}
	
	public String potencyDiff(){
		return potencyDiff;
	}
	
	public void setVariableDiff(String variableDiff){
		this.variableDiff = variableDiff;
	}
	
	public void setPotencyDiff(String potencyDiff){
		this.potencyDiff = potencyDiff; 
	}
}
