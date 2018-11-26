
public class BranchInstruction{

	int address;
	String outcome;

	public BranchInstruction(String address, String outcome){
		this.address = Integer.parseInt(address, 16);
		this.outcome = outcome;

		System.out.println(address +" " + this.outcome);
	
	}



}
