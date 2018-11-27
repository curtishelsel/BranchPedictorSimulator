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
	
	private int m;
	private int n;
	private int[] table;
	private int gbhRegister;

	public BranchPredictor(int m, int n, String fileName){

		this.m = m;
		this.n = n;

		table = new int[(int) Math.pow(2,m)];
		Arrays.fill(table, 2);

		try{
			Scanner in = new Scanner(new File(fileName));

			while(in.hasNext()){
				String address = in.next();
				String outcome = in.next();
				
				BranchInstruction bi = new BranchInstruction(address, outcome);

				simulate(bi);
			}
		
		}
		catch(FileNotFoundException ex){
			System.out.println(ex.toString());		
		}
	}

	private void simulate(BranchInstruction bi){
	
		int offset = bi.getAddressM(m) ^ (int)(gbhRegister * Math.pow(2, m-n));
		int prediction = table[offset];

		System.out.println(offset);
	
	}
	
	
	
	private void printStatistics(){
	
	
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
