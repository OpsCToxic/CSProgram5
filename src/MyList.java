
public interface MyList {

	boolean add(Object data);
	
	void clear();
	
	boolean contains(Object data);
	
	Object get(int index) throws Exception;
	
	//Extra credit
	
	//String remove(int index) throws Exception;
	
	int indexOf(Object data);
	
	boolean isEmpty();
	
	void trimToSize();
	
	int size();
	
	
	
}
