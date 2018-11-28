/* Curtis Helsel
   EEL4768 - Fall 2018
   November 26, 2018

   Branch instruction class to be used in the brach prediction simulator
*/
public class BranchInstruction{

	private int address;
	private int outcome;

	public BranchInstruction(String address, String outcome){
	
		this.address = Integer.parseInt(address, 16);
		
		// Converting taken to integer value 1, not take remains 0
		if(outcome.equals("t"))	this.outcome = 1;
	}

	// Provided the m bits of the address
	public int getAddressM(int m){
		return (address >> 2) % (int) Math.pow(2,m);
	}

	public int getOutcome(){
		return outcome;
	}

}
