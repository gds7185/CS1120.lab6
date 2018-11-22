package edu.wmich.CS1120.LA6;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Decoder implements IDecoder{

	@Override
	public void decode(String filePath) {
		
		System.out.println("Decoding message...\n");
		
		try {
			//Read from encoded file using RandomAccessFile
			RandomAccessFile randomAccess = new RandomAccessFile(filePath, "r");
			randomAccess.seek(0);
			
			boolean end = false;		//keep track of the end of the file
			int numBytes = 0;			//keep track of int that follows each char
			
			while(!end) {
				
				System.out.print(randomAccess.readChar());	//Print current character
				numBytes = randomAccess.readInt();			//Read int following character
				
				//Check if we are at the end of the file
				if(numBytes == -1) {
					end = true;
					System.out.println("\n\nDone encoding message.");
				}
				else {
					
					//Move the cursor past all the "junk" bytes, to next char
					for(int i = 0; i < numBytes; i++) {
						randomAccess.readByte();
					}
				}
			}//end while
			
			randomAccess.close();
			
		}catch (IOException e) {
		
			System.out.println("\n\nError: IOException with Decoder");
		}
	}
}
