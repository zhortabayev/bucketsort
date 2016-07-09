import java.util.ArrayList;
import java.util.Collections;

public class BucketThread implements Runnable{
	
	ArrayList<String> al;
	
	public BucketThread(ArrayList<String> al) {
		this.al = al;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Collections.sort(al);
	}
}
