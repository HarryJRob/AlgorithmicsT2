import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayRing<T> implements Ring<T> {

	private static int DEFAULT_ARRAY_SIZE = 32;
	
	private T[] elementArray;
	private int headAddress = 0;
	private int maxSize;
	private int count;
	
	public CircularArrayRing() {
		this(DEFAULT_ARRAY_SIZE);
	}
	
	public CircularArrayRing(int size) {
		this.maxSize = size;
		elementArray = (T[]) new Object[size];
	}
	
	@Override
	public boolean add(T element) {

		if(elementArray[headAddress] == null)
			count++;
		
		elementArray[headAddress] = element;
		headAddress = (headAddress + 1) % maxSize;

		
		return true;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if(index > count || index > maxSize || index < 0)
			throw new IndexOutOfBoundsException();
		
		int adjustedIndex = headAddress-index-1;
		
		if(adjustedIndex < 0) 
			adjustedIndex += maxSize;
		
		return elementArray[adjustedIndex];
	}
	
	@Override
	public int size() {
		return count;
	}

	@Override
	public Iterator<T> iterator() {

		return new Iterator<T>() {
			
			int curPos = headAddress - 1;
			@Override
			public boolean hasNext() {
					int nextPos = curPos - 1;
					if(nextPos < 0) {
						nextPos = maxSize - 1;
					}
					
					if(nextPos == headAddress) {
						return false;
					} else {
						return true;
					}
			}

			@Override
			public T next() {
				if(hasNext()) {
					
					curPos--;
					if(curPos < 0 ) {
						curPos = maxSize - 1;
					}
					
					return elementArray[curPos];
					
				} else {
					throw new NoSuchElementException();
				}
			}
			
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
	
	
	@Override
	public boolean addAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
