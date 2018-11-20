package edu.wmich.CS1120.LA6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Encoder implements IEncoder{

	@Override
	public void encode(String inputFileName, String outputFilePath) {
		// TODO Auto-generated method stub	
		String fileRead = "";
		int count = 0;
		try {
			File f = new File(inputFileName);
			Scanner readFile = new Scanner(f);
			while (readFile.hasNext()) {
				fileRead = fileRead + readFile.nextLine();
				count++;
			}
			readFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: input file not found");
		}
		System.out.println("Content of input file: \n" + fileRead);
		System.out.println(count);
	}

}
