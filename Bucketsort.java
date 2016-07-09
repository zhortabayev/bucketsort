import java.io.*;
import java.util.*;

public class Bucketsort {
	
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();

		if(args.length != 1)
			System.out.println("Please enter in correct format"
					+ ": 'java program_name inputfile'");		
		
		String input = args[0];
		String output = "bucketsort.out";
		
		Map<Integer, ArrayList<String>> bucketsAndKeys = new HashMap<Integer, ArrayList<String>>();		
		
		try {
			FileReader fileReader = new FileReader(input);			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			String tempStr = "";
			
			while ((tempStr = bufferedReader.readLine()) != null) {

				int tempInt = (int) tempStr.charAt(0);
				
				if(bucketsAndKeys.get(tempInt) == null) {
					ArrayList<String> al = new ArrayList<String>();
					al.add(tempStr);
					bucketsAndKeys.put(tempInt, al);					
				}
				else {
					bucketsAndKeys.get(tempInt).add(tempStr);					
				}				
			}
			
			bufferedReader.close();
		}		
		catch(FileNotFoundException fnfe) {
			System.out.println("Unable to open file '" + input + "'");
		}		
		catch(IOException ioe) {
			System.out.println("Error reading file '" + input + "'");
		}
		
		Thread [] threadsArray = new Thread[100];
		
		int threadIndex = 0;
		for(int k: bucketsAndKeys.keySet()) {
			BucketThread bt = new BucketThread(bucketsAndKeys.get(k));
			threadsArray[threadIndex] = new Thread(bt);
			threadIndex++;
		}
		
		for(int i = 0; i < threadIndex; i++) {
			threadsArray[i].start();			
		}
		
		for(int i = 0; i < threadIndex; i++) {
			try {
				threadsArray[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			FileWriter fileWriter = new FileWriter(output);			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		
			
			for(int key: bucketsAndKeys.keySet()) {
				ArrayList<String> al = bucketsAndKeys.get(key);
				bufferedWriter.write(key + " : ");
				for(String str: al)
					bufferedWriter.write(str + ", ");
				bufferedWriter.write("\n");
			}			
			
			bufferedWriter.close();
		}
		catch(IOException ioe) {
			System.out.println("Error wrtiting to file '" + output + "'");
		}
		
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("The time spent is: "  + elapsedTime);
	}

}