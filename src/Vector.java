/**
 * Program 5
 *  This program creates a Vector class using interfaces, abstract classes, and generic types that behave like a dynamic list structure using an indexed array. 
 *  The program includes methods that insert and remove elements from the back of the list, which is unique to the vector class. Other methods including insert and erase perform similar functions as other list data structures.
 *  4/6/22
 *  @author  Cameron Cobb
 * 
 */

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Vector<E> extends AbstractListADT<E> implements Iterable<E> {

	protected E[] array;
	protected int size;
	protected int capacity;
	protected E current;
	protected int count; 
	
	/**
	 * This constructor initializes a new array of type E with the default length of 10, size of 0, and capacity to the default capacity constant.
	 */
	@SuppressWarnings("unchecked")
	public Vector() {
		array = (E[]) new Object[10];
		size = 0;
		capacity = DEFAULT_CAPACITY;
	}
	
	/**
	 * This constructor initializes a new array of type E with the length set to the capacity, size of 0, and capacity to the capacity specified by the object.
	 * @param capacity
	 */
	@SuppressWarnings("unchecked")
	public Vector(int capacity) {
		array = (E[]) new Object[capacity];
		size = 0;
		capacity = this.capacity;
	}
	
	/**
	 * This constructor initializes a new array of type E with the length set to the capacity of an existing vector, and each element of the vector assigned to the new array. THe size and capacity is assigned with the respective vector values.
	 * @param vector
	 */
	@SuppressWarnings("unchecked")
	public Vector(Vector<E> vector) {
		array = (E[]) new Object[vector.capacity];
		for(int i = 0; i < vector.capacity; ++i) {
			array[i] = vector.at(i);
			
		}
		size = vector.size;
		capacity = vector.capacity;
	}
	

	/**
	 * This getter method returns the capacity of the array.
	 */
	@Override
	public int capacity() {
		return array.length;
		
	}

	/**
	 * This getter method returns the front of the array, the first element.
	 */
	@Override
	public E front() {
		return array[0];
		
	}
	
	/**
	 * This getter method returns the back of the array, or the last element.
	 */
	@Override
	public E back() {
		return array[size - 1];
	}
	
	/**
	 * This getter method returns the reference variable or the address of the array.
	 */
	@Override
	public E[] data() {
	
		return array;
	}

	/**
	 * This method inserts an element at the end of the list. If the array is full, the checkCapacity helper method is called, increasing the length of the array to double its current size.
	 */
	@Override
	public void pushback(E element) {
		if (size + 1 >= array.length) {
			resize(array.length * 2);
			
		}
		
		array[size++] = element;
		
		
	}
	
	
	/**
	 * This method returns the last element of the list and decreases the size by 1, shortening the list.
	 */
	@Override
	public E popback() {
		E temp = array[size - 1];
		size--;
		return temp;

		
	}

	/**
	 * This method inserts an element at the specified index called insertPosition. A new array is created of generic type to copy the elements over from the original array and insert the element, adjusting the preceding elements accordingly.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void insert(int insertPosition, E element) {
		if (size + 1 >= array.length) {
			resize(array.length * 2);
			
		}
		
		E[] newArray = (E[]) new Object[capacity];
		
		for (int i = 0; i < insertPosition; ++i) {
			newArray[i] = array[i];
		}
		
		newArray[insertPosition] = element;
		
		for (int i = insertPosition + 1; i < capacity; ++i) {
			newArray[i] = array[i - 1];
		}
		
		array = newArray;
		size++;
		
	}

	/**
	 * This method erases the element at the specified index of the array.
	 */
	@Override
	public void erase(int position) {
		if (size > 0) {
			for (int i = position; i < size - 1; ++i) {
				array[i] = array[i + 1];
			}
			size--;
		}
		
		
	}

	/**
	 * This method erases the elements of the array from the first parameter up until the second parameter.
	 */
	@Override
	public void erase(int startRangePosition, int endRangePosition) {
		int index = 1;
		if (size > 0) {
			for (int i = startRangePosition; i < size; ++i) {
				array[i] = array[endRangePosition - 1 + index];
				index++;

			}
		}
		size -= endRangePosition - startRangePosition;
		
	}

	/**
	 * This method swaps the contents of a Vector and assigns them to the array.
	 */
	@Override
	public void swap(Vector<E> other) {
		
		for (int i = 0; i < other.size; ++i) {
			array[i] = other.at(i);
			
		}
		size = other.size;
		capacity = other.capacity;
	}

	/**
	 * This method creates a new temp array whos capacity is equal to the size of the original array and copies each element over to the new array until size ahs been reached.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void shrinkToFit() {
		
		E[] temp = (E[]) new Object[size];
		for (int i = 0; i < size; ++i) {
			temp[i] = array[i];
		}
		array = temp;
	}

	/**
	 * This method resizes an array by creating a new temp array of capacity equal to the newSize parameter and copies each element from the original array to the new one.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void resize(int newSize) {
		E[] newArray = (E[]) new Object[newSize];
		for (int i = 0; i < array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
		capacity = newSize;
		
	}

	/**
	 * This method creates a new Iterator from the method iterator() and returns the Iterator.
	 */
	@Override
	public Iterator<E> begin() {
		Iterator<E> it = iterator();
		return it;
	}

	/**
	 * This method creates a new Iterator from the constructor of the ArrayIterator private class.
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}



private class ArrayIterator implements Iterator<E> {

	/**
	 * Creates the current variable at the start of the array and the count equal to 1, the number of elements in the list that have been passed.
	 */
	private ArrayIterator() {
		current = array[0];
		count = 1;
	}
	
	
	/**
	 * This method returns a boolean depending on if the array contains another element after the current index.
	 */
	@Override
	public boolean hasNext() {
		
		return count <= size;
	}

	/**
	 * This method returns the next variable of the array, depending on if the array contains another element.
	 *
	 */
	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		else {
			current = array[count - 1];
			count++;
			
			return current;
		}
	}
	
}

private class ReverseArrayIterator implements Iterator<E> {

	/**
	 * Creates the current variable at the start of the array and the count equal to 1, the number of elements in the list that have been passed.
	 */
	private ReverseArrayIterator() {
		current = array[size - 1];
		count = 1;
	}
	
	
	/**
	 * This method returns a boolean depending on if the array contains another element after the current index.
	 */
	@Override
	public boolean hasNext() {
		
		return count <= size;
	}

	/**
	 * This method returns the next variable of the array, depending on if the array contains another element.
	 *
	 */
	@Override
	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		else {
			current = array[size - count];
			count++;
			
			return current;
		}
	}
	
}
	/**
	 * This getter method returns the element of the array at the specified index.
	 */
	@Override
	public E at(int index) {
		
		return array[index];
	}

	/**
	 * This getter method returns the current size of the array.
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * This method returns a boolean if the array is empty or not.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * This method sets the array size to 0, clearing all of the elements of the array.
	 */
	@Override
	public void clear() {
		size = 0;
	}


	/**
	 * This method resizes the vector array if the capacity is less than the reservesize.
	 */
	public void reserve(int reserveSize) {
		if (capacity < reserveSize) {
			resize(reserveSize);
		}
		
	}

	/**
	 * This method returns an iterator of the ReverseArrayIterator class.
	 */
	public Iterator<E> rbegin() {
		Iterator<E> it = new ReverseArrayIterator();
		return it;
	}
	
	
}
