
public class BranchInstruction{

	private int address;
	private int outcome;

	public BranchInstruction(String address, String outcome){
	
		this.address = Integer.parseInt(address, 16);
		
		if(outcome.equals("t"))	this.outcome = 1;
	}

	public int getAddressM(int m){
		return (address >> 2) % (int) Math.pow(2,m);
	}

	public int getOutcome(){
		return outcome;
	}

}
