/* Curtis Helsel
   EEL4768 - Fall 2018
   November 26, 2018

   This program simulates a global branch predictor.
*/

public class BranchPredictor{
	
	private int m;
	private int n;
	private int[] table;

	public BranchPredictor(int m, int n, String fileName){

		this m = m;
		this n = n;

		

		table = new int[Math.pow(2,m)];
	}
	
	
	
	private void printStatistics(){
	
	
	}
	
	public static void main(String[] args){
	
		BranchPredictor bp = new BranchPredictor(
				Integer.parseInt(args[0]),
				Integer.parseInt(args[1]),
				args[2]);
	
		bp.printStatistics();
	}














}
