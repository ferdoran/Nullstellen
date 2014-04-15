package parser;

public class VPObject {
	private String variable; //ohne x, da es für die Ableitung nicht relevant ist
	private String potency;
	private String derivationVariable; //ohne x, da es für die Ableitung nicht relevant ist
	private String derivationPotency;
	
	public VPObject(String constant, String potency){
		this.variable = constant;
		this.potency = potency;
		derivationVariable = "";
		derivationPotency = "";
	}
	
	public String variable(){
		return variable;
	}
	
	public String potency(){
		return potency;
	}
	
	public String derivationVariable(){
		return derivationVariable;
	}
	
	public String derivationPotency(){
		return derivationPotency;
	}
	
	public void setDerivationVariable(String variableDiff){
		this.derivationVariable = variableDiff;
	}
	
	public void setDerivationPotency(String potencyDiff){
		this.derivationPotency = potencyDiff; 
	}
}
