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
	
		int index = bi.getAddressM(m) ^ (gbhRegister * (int)Math.pow(2, m-n));
		int prediction = predictionTable[index] / 2;
	
		if(prediction != bi.getOutcome()){
			miss++;
		}
			
		gbhRegister = gbhRegister >> 1;
		
		if(bi.getOutcome() == 1){
			if(predictionTable[index] < 3){
				predictionTable[index]++;
			}
			gbhRegister += (int) Math.pow(2,n-1);
		}
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
