
public class BranchInstruction{

	private int address;
	private String outcome;

	public BranchInstruction(String address, String outcome){
		this.address = Integer.parseInt(address, 16);
		this.outcome = outcome;
	
	}


	public int getAddressM(int m){
		return (address / 2) % (int) Math.pow(2,m);
	}

	public String getOutcome(){
		return outcome;
	}

}
