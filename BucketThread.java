import java.util.Arrays;

public class BucketThread extends Thread{	
	String [] arr;
	
	public BucketThread(String [] arr) {
		this.arr = arr;
	}
	
	public BucketThread(String [] arr, String [] arr2) {
		this.arr = arr;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Arrays.sort(arr);
	}
}
