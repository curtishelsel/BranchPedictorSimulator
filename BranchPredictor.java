/* Curtis Helsel
   EEL4768 - Fall 2018
   November 26, 2018

   This program simulates a global branch predictor.
*/

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class BranchPredictor{
	
	private int m, n, miss, total, gbhRegister;
	private int[] predictionTable;

	public BranchPredictor(int m, int n, String fileName){

		this.m = m;
		this.n = n;

		// Set prediction table initial values to weakly taken
		predictionTable = new int[(int) Math.pow(2,m)];
		Arrays.fill(predictionTable, 2);

		try{
			Scanner in = new Scanner(new File(fileName));

			while(in.hasNext()){
				String address = in.next();
				String outcome = in.next();
				
				BranchInstruction bi = new BranchInstruction(address, outcome);

				simulate(bi);
				total++;
			}
		
		}
		catch(FileNotFoundException ex){
			System.out.println(ex.toString());		
		}
	}

	private void simulate(BranchInstruction bi){
	
		// index of table is the branch address M value xor with the gbhRegister value
		int index = bi.getAddressM(m) ^ (gbhRegister * (int)Math.pow(2, m-n));

		// Changes values 0-3 to either 0 or 1 for not taken or taken, respectively
		int prediction = predictionTable[index] / 2;
	
		// If the prediction is wrong, increment the number of misses
		if(prediction != bi.getOutcome()){
			miss++;
		}
		
		// Every iteration will shift in the next outcome into the gbhRegister
		gbhRegister = gbhRegister >> 1;
		
		// If the outcome is taken, increment prediction value
		if(bi.getOutcome() == 1){
			if(predictionTable[index] < 3){
				predictionTable[index]++;
			}
			// Add 1 to the beginning of the gbhRegister
			gbhRegister += (int) Math.pow(2,n-1);
		}
		// If the outcome is not taken, decrement the prediction value
		else{
			if(predictionTable[index] > 0)
				predictionTable[index]--;
		}
	}
	
	private void printStatistics(){
		System.out.printf("%d %d %.2f%% \n",m , n, ((double) miss / total) * 100);
	}
	
	public static void main(String[] args){
	
		if(args.length != 3){
			System.out.println("Please provide the correct amount of arguments.");
			return;
		}

		BranchPredictor bp = new BranchPredictor(
				Integer.parseInt(args[0]),
				Integer.parseInt(args[1]),
				args[2]);
	
		bp.printStatistics();
	}
}
