package parser;

public class VPObject {
	private String variable; //ohne x, da es für die Ableitung nicht relevant ist
	private String potency;
	
	public VPObject(String constant, String potency){
		this.variable = constant;
		this.potency = potency;
	}
	
	public String variable(){
		return variable;
	}
	
	public String potency(){
		return potency;
	}
}
