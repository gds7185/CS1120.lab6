package edu.wmich.CS1120.LA6;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Encoder implements IEncoder{

	@Override
	public void encode(String inputFileName, String outputFilePath) {

		String fileRead = "";
		
		try {
			File f = new File(inputFileName);
			Scanner readFile = new Scanner(f);
			
			while (readFile.hasNext()) {
				
				fileRead = fileRead + readFile.nextLine();
			}
			
			readFile.close();
		} 
		catch (FileNotFoundException e) {
			
			System.out.println("Error: input file not found");
		}
		
		System.out.println("Content of input file: \n" + fileRead);
		//System.out.println(count);
		Random rand = new Random();
		int n = 0;
		System.out.println("Now encoding the file...");
		try {
			FileOutputStream toFile = new FileOutputStream(outputFilePath, true);
			DataOutputStream output = new DataOutputStream(toFile);
			
			for(int i = 0; i < fileRead.length(); i ++) {
				
				n = rand.nextInt(20);	
				String a = "";
				a = a + fileRead.charAt(i);
				int h = a.codePointAt(0);
				output.writeChar(h);
				//output.writeUTF(a);
				output.writeInt(n);
				output.writeByte(n);
			}
			output.writeInt(-1);
			//System.out.println(output);
			output.close();
		} 
		catch (FileNotFoundException e) {
			
			System.out.println("Error: Output file not found in Encoder");
		}
		catch (IOException e) {
			
			System.out.println("Error: problem with the encoder");
		}
		System.out.println("Done.");
	}
}
