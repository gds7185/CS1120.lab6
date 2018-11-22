package edu.wmich.CS1120.LA6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class Encoder implements IEncoder{

	public void encode(String inputFileName, String outputFilePath) {

		//Stores information from txt file
		String fileRead = "";
		
		try {
			
			//Read file with scanner
			File f = new File(inputFileName);
			Scanner readFile = new Scanner(f);
			
			//Store txt file into fileRead string
			while (readFile.hasNext()) {
				
				fileRead += readFile.nextLine();
			}
			readFile.close();
		} 
		catch (FileNotFoundException e) {		//Error handling
			
			System.out.println("Error: input file not found");
		}
		
		System.out.println("Content of input file: \n\n" + fileRead);

		Random rand = new Random();				//random object and variable
		int n = 0;
		
		try {
			
			//Using a RandomAccessFile to write our bytes to a binary file
			RandomAccessFile randomAccess = new RandomAccessFile(outputFilePath, "rw");
			randomAccess.seek(0);
			
			System.out.println("\nEncoding message...");
			
			//Iterate once for each character in message
			for(int i = 0; i < fileRead.length(); i ++) {	
				
				//Set n to Random value from 1 to 20
				n = rand.nextInt(20) + 1;	
				
				//Create byte array the size of n
				byte[] bytes = new byte[n];
				
				//Populate bytes array with random values
				rand.nextBytes(bytes);
				
				randomAccess.writeChar(fileRead.charAt(i));	//Write character
				
				//If we're on the last character
				if(i == fileRead.length() - 1) {
					
					randomAccess.writeInt(-1);	//Set final value to -1
				}
				else {
					
					randomAccess.writeInt(n);	//Write int (number of random bytes)
					
					//Iterate through random bytes and write each value
					for(int j = 0; j < bytes.length; j++) {
						
						randomAccess.writeByte(bytes[j]);
					}
				}
			}
			randomAccess.close();
		} 
		catch (FileNotFoundException e) {
			
			System.out.println("Error: Output file not found in Encoder");
		}
		catch (IOException e) {
			
			System.out.println("Error: problem with the encoder");
		}
		
		System.out.println("\nDone encoding message.\n");
	}
}
