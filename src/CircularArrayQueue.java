import java.util.NoSuchElementException;

public class CircularArrayQueue implements MyQueue {

	private static int INITIAL_ARRAY_SIZE = 8; 
	
	private Integer[] integerArray = new Integer[INITIAL_ARRAY_SIZE];
	private int headAddress = 0;
	private int tailAddress = 0;
	private int count = 0;
	
	@Override
	public void enqueue(int in) {

		if(count == integerArray.length) {
			Integer[] tempArray = new Integer[integerArray.length*2];
			
			for (int curAddress = 0; curAddress < count; curAddress++) { 
				tempArray[curAddress] = integerArray[tailAddress];
				tailAddress++;
				tailAddress%=integerArray.length;
			}
			
			tailAddress = 0;
			headAddress = count;
			integerArray = tempArray;
		}
		
		integerArray[headAddress] = in;
		headAddress++;
		headAddress%=integerArray.length;
		count++;
	}

	@Override
	public int dequeue() throws NoSuchElementException {
		if(count == 0)
			throw new NoSuchElementException();
		
		int element = integerArray[tailAddress];
		integerArray[tailAddress] = null;
		
		tailAddress++;
		tailAddress%=integerArray.length;
		
		count--;
		
		return element;
	}

	@Override
	public int noItems() {
		return count;
	}

	@Override
	public boolean isEmpty() {
		if(count == 0) {
			return true;
		} else {
			return false;	
		}
	}

	public int getCapacityLeft() {
		return integerArray.length - count;
	}
	
}
