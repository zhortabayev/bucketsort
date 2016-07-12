import java.util.Arrays;

public class BucketThread extends Thread{
	
	String [] arr;
	String [] arr2;
	
	public BucketThread(String [] arr) {
		this.arr = arr;
	}
	
	public BucketThread(String [] arr, String [] arr2) {
		this.arr = arr;
		this.arr2 = arr2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Arrays.parallelSort(arr);
		if(arr2 != null) Arrays.parallelSort(arr2);		
	}
}
