import java.io.*;
import java.util.*;

public class Bucketsort {
	
	public static void main(String[] args) {
		
		double startTime = System.currentTimeMillis();
		
		int chars = 94;
		int size = 0;

		if(args.length != 1)
			System.out.println("Please enter in correct format"
					+ ": 'java program_name inputfile'");		
		
		String input = args[0];
		String output = "bucketsort.out";
				
		Map<Integer, String []> buckets = new HashMap<Integer, String []>();		

		try {
			FileReader fileReader = new FileReader(input);			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			size = Integer.valueOf(bufferedReader.readLine()) / chars;
			String tempStr = "";
			
			int [] sizes = new int[chars];

			while ((tempStr = bufferedReader.readLine()) != null) {
				int tempInt = (int) tempStr.charAt(0);				
				if(buckets.get(tempInt) == null) {
					String [] arr = new String[size];
					arr[0] = tempStr;
					buckets.put(tempInt, arr);	
					sizes[tempInt - 33] += 1;
				}
				else {
					buckets.get(tempInt)[sizes[tempInt - 33]] = tempStr;
					sizes[tempInt - 33] += 1;
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

		for(int k: buckets.keySet()) 	
			new BucketThread(buckets.get(k)).start();
		while(Thread.activeCount() != 1) {
			/*wait until finished*/
		}
				
		try {
			FileWriter fileWriter = new FileWriter(output);			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);		
			
			for(int k: buckets.keySet()) {
				bufferedWriter.write(k + " : ");
				for(int i = 0; i < size; i++) 
					bufferedWriter.write(buckets.get(k)[i] + ", ");
				bufferedWriter.write("\n");
			}			
			
			bufferedWriter.close();
		}
		catch(IOException ioe) {
			System.out.println("Error wrtiting to file '" + output + "'");
		}
		
		double stopTime = System.currentTimeMillis();
		double elapsedTime = (stopTime - startTime) / 1000;
	    System.out.println("The time spent is: "  + elapsedTime + "s");
	}

}