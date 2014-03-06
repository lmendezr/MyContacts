package edu.uprm.ece.icom4035.mycontacts.managers;

import java.util.Iterator;

/**
 * SortedList is an ADT interface for the SortedArrayList implementation.
 * 
 * <h4>Description</h4>
 * 
 * This is an an abstract data type of a list which provides an interface to
 * various operations, such as adding contacts in a sorted manner by comparing
 * each object to be added to the objects currently stored in the list.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.5
 * 
 */
public interface SortedList<E extends Comparable<E>> extends Iterable<E> {

	/**
	 * Creates an instance of the Iterator class inside the list. Required in
	 * order to enable iteration used later in the program.
	 * 
	 * @param index
	 *            The index of the starting position in the iteration of the
	 *            list.
	 * 
	 * @return Allows the class to be used as a collection with the enhanced for
	 *         loop.
	 */

	public Iterator<E> iterator(int index);

	/**
	 * Adds a new element to the list.
	 * 
	 * @param obj
	 *            Reference to the new element.
	 * 
	 * @return A boolean value indicating if the operation was successful.
	 **/
	public boolean add(E obj);

	/**
	 * Removes an element from the list.
	 * 
	 * @param obj
	 *            Reference to the element.
	 * 
	 * @return A boolean value indicating if the operation was successful.
	 **/
	public boolean remove(E obj);

	/**
	 * Removes an element from the list.
	 * 
	 * @param index
	 *            The index value of the position whose element is being deleted
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Whenever the value of index is not in the valid range from 0
	 *             to size-1.
	 * 
	 * @return A boolean value indicating if the operation was successful.
	 **/
	public boolean remove(int index);

	/**
	 * Removes all instances of an element from the list.
	 * 
	 * @param obj
	 *            Reference to the element.
	 * 
	 * @return A boolean value indicating if the operation was successful.
	 **/
	public int removeAll(E obj);

	/**
	 * Removes all elements from the list.
	 **/
	public void clear();

	/**
	 * Determines if the list contains an element.
	 * 
	 * @param obj
	 *            Reference to the element.
	 * 
	 * @returns True if the list contains the element, false if not.
	 **/
	public boolean contains(E obj);

	/**
	 * Accesses an element in the list.
	 * 
	 * @param index
	 *            The index value of the position whose element is being
	 *            requested
	 * 
	 * @throws IndexOutOfBoundsException
	 *             Whenever the value of index is not in the valid range from 0
	 *             to size-1.
	 * 
	 * @return Reference to the particular element.
	 **/
	public E get(int index);

	/**
	 * Accesses the first element in the list.
	 * 
	 * @return Reference to the particular element.
	 **/
	public E first();

	/**
	 * Accesses the last element in the list.
	 * 
	 * @return Reference to the particular element.
	 **/
	public E last();

	/**
	 * Accesses the first occurrence of an element in the list.
	 * 
	 * @param obj
	 *            Reference to the element.
	 * 
	 * @return Index of the particular element in the list.
	 **/
	public int firstIndex(E obj);

	/**
	 * Accesses the last occurrence of an element in the list.
	 * 
	 * @param obj
	 *            Reference to the element.
	 * 
	 * @return Index of the particular element in the list.
	 **/
	public int lastIndex(E obj);

	/**
	 * Determines the size of the current list instance.
	 * 
	 * @return Number of elements in the list.
	 **/
	public int size();

	/**
	 * Determines if the list is empty or not.
	 * 
	 * @returns true if empty, false if not.
	 **/
	public boolean isEmpty();

}
