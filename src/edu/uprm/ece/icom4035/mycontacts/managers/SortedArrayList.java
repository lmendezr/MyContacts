package edu.uprm.ece.icom4035.mycontacts.managers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SortedArrayList is an implementation of SortedList that keeps the elements in
 * the list sorted in increasing order.
 * 
 * <h4>Description</h4>
 * 
 * This implementation is essentially similar to the implementation of a List.
 * The only difference lies within the <strong>add</strong> method, which stores
 * every added object sorted in increasing order. Being an implementation of the
 * SortedList, it provides all of the methods established by this ADT, such as
 * iteration capabilities.
 * 
 * <h4>Notes</h4>
 * 
 * The user of this class needs to be careful when modifying items already added
 * to an instance of an SortedArrayList, since it only sorts when new items are
 * added into the list itself.
 * 
 * @author Lixhjideny M�ndez R�os
 * 
 * @version 1.5
 * 
 */
@SuppressWarnings({ "unchecked", "hiding" })
public class SortedArrayList<E extends Comparable<E>> implements SortedList<E> {

	// Constant for default constructor
	private static final int DEFAULT_INITIAL_CAPACITY = 10;

	// Instance variables
	private int currentSize;
	private E elements[];

	private class ListIterator<E> implements Iterator<E> {
		private int currentPosition;

		public ListIterator() {
			this.currentPosition = 0;
		}

		public ListIterator(int index) {
			this.currentPosition = index;
		}

		@Override
		public boolean hasNext() {
			return this.currentPosition < size();
		}

		@Override
		public E next() {
			if (this.hasNext()) {
				return (E) elements[this.currentPosition++];
			} else {
				throw new NoSuchElementException();
			}
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	// Constructors
	/**
	 * Creates a SortedArrayList with the default initialization
	 * (DEFAULT_INITIAL_CAPACITY).
	 */
	public SortedArrayList() {
		this.currentSize = 0;
		this.elements = (E[]) new Comparable[DEFAULT_INITIAL_CAPACITY];
	}

	/**
	 * Creates a SortedArrayList with the provided initial capacity.
	 * 
	 * @param initialCapacity
	 *            The amount of space the internal array should start with.
	 */
	public SortedArrayList(int initialCapacity) {
		if (initialCapacity < DEFAULT_INITIAL_CAPACITY) {
			throw new IllegalArgumentException("Capacity must be at least "
					+ DEFAULT_INITIAL_CAPACITY + " .");
		}
		this.currentSize = 0;
		this.elements = (E[]) new Comparable[initialCapacity];
	}

	/**
	 * Adds a new element to the list, sorted in increasing order. If an
	 * element(s) with the same properties already exists in the list, the new
	 * element is added after the element(s) with the same properties.
	 * 
	 * @param obj
	 *            Reference to the new element.
	 * 
	 * @return A boolean value indicating if the operation was successful.
	 **/
	@Override
	public boolean add(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException(
					"Argument object cannot be null.");
		} else {
			if (this.currentSize == this.elements.length) {
				reAllocate();
			}
			int target = -1;
			for (int i = 0; i < this.size(); ++i) {
				if (obj.compareTo(this.elements[i]) < 0) {
					target = i;
					break;
				}
			}
			if (target == -1) {
				this.elements[this.currentSize++] = obj;
				return true;
			} else {
				System.arraycopy(elements, target, elements, target + 1,
						this.currentSize - target);
			}
			this.elements[target] = obj;
			this.currentSize++;
			return true;
		}
	}

	/**
	 * Reallocates the elements in the array to a new array with twice the size.
	 * This allows dynamic storage in case that the add method requires
	 * additional space.
	 **/
	// Helper method for add
	private void reAllocate() {
		// create a new array with the twice the size
		E newElements[] = (E[]) new Comparable[2 * this.elements.length];
		int currentSize = this.size();
		// copy all values into the new array
		System.arraycopy(this.elements, 0, newElements, 0, this.currentSize);
		// replace old elements with newElements
		this.clear();
		this.elements = newElements;
		this.currentSize = currentSize;
	}

	@Override
	public boolean remove(E obj) {
		if (obj == null) {
			throw new IllegalArgumentException(
					"Argument object cannot be null.");
		}
		// first find obj in the array
		int target = -1;
		for (int i = 0; i < this.currentSize; ++i) {
			if (this.elements[i].equals(obj)) {
				// found it
				target = i;
				break;
			}
		}
		if (target == -1) {
			return false;
		} else {
			System.arraycopy(this.elements, target + 1, this.elements, target,
					this.currentSize - 1 - target);
			// reduce size of list and remove extra last reference
			this.elements[--this.currentSize] = null;
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		if (index >= 0 && index < this.currentSize) {
			System.arraycopy(this.elements, index + 1, this.elements, index,
					this.currentSize - 1 - index);
			// reduce size of list and remove extra last reference
			this.elements[--this.currentSize] = null;
			return true;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public int removeAll(E obj) {
		int counter = 0;
		while (this.remove(obj)) {
			counter++;
		}
		return counter;
	}

	@Override
	public void clear() {
		for (int i = 0; i < this.currentSize; ++i) {
			this.elements[i] = null;
		}
		this.currentSize = 0;
	}

	// Getters

	@Override
	public E get(int index) {
		if (index >= 0 && index < this.size()) {
			return this.elements[index];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	@Override
	public E first() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements[0];
		}
	}

	@Override
	public E last() {
		if (this.isEmpty()) {
			return null;
		} else {
			return this.elements[this.currentSize - 1];
		}
	}

	@Override
	public int firstIndex(E e) {
		for (int i = 0; i < this.currentSize; ++i) {
			if (this.elements[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndex(E e) {
		for (int i = this.currentSize - 1; i >= 0; --i) {
			if (this.elements[i].equals(e)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return this.currentSize;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean contains(E e) {
		return this.firstIndex(e) >= 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>();
	}

	@Override
	public Iterator<E> iterator(int index) {
		return new ListIterator<E>(index);
	}

}
