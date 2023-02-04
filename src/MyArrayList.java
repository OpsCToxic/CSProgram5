import java.util.Arrays;

//import java.util.ArrayList;

/**
 * @author ccobb
 *
 */
public class MyArrayList implements MyList {

	protected Object[] array;
	protected int size;
	
	public MyArrayList() {
		array = new Object[10];
		size = 0;
	}
	public static void main(String[] args) throws Exception {
		MyArrayList list = new MyArrayList();
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("One");
		list.add("Two");
		list.add("Three");
		list.add("One");
		list.add("Two");
		list.add("Three");
		//list.add(2, "Four");
		for (int i = 0; i < list.size(); ++i) {
			System.out.println(list.get(i));
		}
	}

	
	/**
	 * Appends the specified Object to the end of the list
	 * @param data
	 * @return boolean
	 */
	@Override
	public boolean add(Object data) {
		// if room in the array, add data to list
		verifyCapacity(size + 1);
		array[size++] = data;
		return false;
	}

	private void verifyCapacity(int need) {
		if (need >= array.length) { // array is full
			resize();
		}
		
	}
	private void resize() {
		Object[] temp = null;
		try {
			temp = new Object[array.length * 2];
			// copy data
			temp = Arrays.copyOf(array, temp.length); //cuts off at 10
		}
		catch (ArrayIndexOutOfBoundsException e) {
			// do something?
		}
		this.array = temp; //old array to garbage collection
		
	}
	@Override
	public void clear() {
	
		
	}

	@Override
	public boolean contains(Object data) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * Returns the Object at the specified position in this list
	 * @param indx
	 * @return Object
	 */
	@Override
	public Object get(int index) throws Exception {
		Object data = array[index]; //return but don't remove
		
		return data;
	}

	@Override
	public int indexOf(Object data) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void trimToSize() {
		
		
	}

	
	
	
}
