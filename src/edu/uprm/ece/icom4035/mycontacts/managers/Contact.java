package edu.uprm.ece.icom4035.mycontacts.managers;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Contact is a basic data structure that stores contact details in an array and
 * addresses in an ArrayList.
 * 
 * <h4>Description</h4>
 * 
 * This is an implementation of an data type which handles specific information
 * for each instance created, provided that the array with whom it was
 * initialized is unique (not a reference to a previously provided array).
 * 
 * <h4>Notes</h4>
 * 
 * Each contact can be initialized with a unique identifier using the
 * alternative constructor. This can only be achieved when creating an instance
 * of this class, since only getters are provided for this specific value.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.5
 * 
 */

public class Contact implements Comparable<Contact>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2961076659103082117L;
	/**
	 * Represents the maximum amount of fields each instance of a Contact can
	 * support. To be used during initialization of the string array to be
	 * passed during initialization of each Contact instance.
	 */
	public static final int TOTAL_DETAILS = 5;
	/**
	 * Retrieves the first index in the array. To be used in conjunction with
	 * the getSpecificDetail Method.
	 */
	public static final int FIRST_NAME = 0;
	/**
	 * Retrieves the second index in the array. To be used in conjunction with
	 * the getSpecificDetail Method.
	 */
	public static final int LAST_NAME = 1;
	/**
	 * Retrieves the third index in the array. To be used in conjunction with
	 * the getSpecificDetail Method.
	 */
	public static final int CELL_PHONE = 2;
	/**
	 * Retrieves the fourth index in the array. To be used in conjunction with
	 * the getSpecificDetail Method.
	 */
	public static final int WORK_PHONE = 3;
	/**
	 * Retrieves the fifth index in the array. To be used in conjunction with
	 * the getSpecificDetail Method.
	 */
	public static final int EMAIL = 4;

	// Instance variables
	private String[] contactDetails = new String[TOTAL_DETAILS];
	private ArrayList<Address> contactAddresses = new ArrayList<Address>();
	private int ID = 0;

	// Constructors
	/**
	 * Creates a contact with the default initialization (non-unique ID).
	 * 
	 * @param contactDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public Contact(String[] contactDetails) {
		this.contactDetails = contactDetails;
	}

	/**
	 * Creates a contact with a unique identification (ID) field.
	 * 
	 * @param contactDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 * 
	 * @param ID
	 *            A integer value with the desired unique identification number.
	 */
	public Contact(String[] contactDetails, int ID) {
		this.contactDetails = contactDetails;
		this.ID = ID;
	}

	// Getters
	/**
	 * @return The unique ID for this instance of a Contact.
	 */
	public int getID() {
		return this.ID;
	}

	/**
	 * @return A String Array with the details of a Contact instance.
	 */
	public String[] getDetails() {
		return this.contactDetails;
	}

	/**
	 * @return A specific detail of a Contact instance.
	 * 
	 * @param fieldIndex
	 *            The index of the desired specific detail. Should use the
	 *            static fields provided by the class for this method. Expects a
	 *            value within the range 0 to TOTAL_DETAILS.
	 */
	public String getSpecificDetail(int fieldIndex) {
		return this.contactDetails[fieldIndex];
	}

	/**
	 * @return The ArrayList of all the addresses in a Contact instance.
	 */
	public ArrayList<Address> getAddresses() {
		return this.contactAddresses;
	}

	/**
	 * @return A String Array with the details of a specific address in a
	 *         Contact instance.
	 * 
	 * @param addressIndex
	 *            The index of the desired address. Expects a value within the
	 *            range 0 to getTotalAddresses-1.
	 */
	public String[] getAddress(int addressIndex) {
		return this.contactAddresses.get(addressIndex).getAddressDetails();
	}

	/**
	 * @return A specific detail of a specific address in a Contact instance.
	 * 
	 * @param addressIndex
	 *            The index of the desired address. Expects a value within the
	 *            range 0 to getTotalAddresses-1.
	 * 
	 * @param fieldIndex
	 *            The index of the desired specific detail. Should use the
	 *            static fields provided by the class for this method. Expects a
	 *            value within the range 0 to TOTAL_ADDRESS_DETAILS.
	 */
	public String getAddressSpecificDetail(int addressIndex, int fieldIndex) {
		return this.contactAddresses.get(addressIndex)
				.getAddressSpecificDetail(fieldIndex);
	}

	/**
	 * @return The amount of addresses inside a Contact instance.
	 */
	public int getTotalAddresses() {
		return this.contactAddresses.size();
	}

	// Setters
	/**
	 * Replaces the details of a Contact instance.
	 * 
	 * @param contactDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void editContact(String[] contactDetails) {
		this.contactDetails = contactDetails;
	}

	/**
	 * Adds a new element to the ArrayList of addresses of a Contact instance.
	 * 
	 * @param addressDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void addAddress(String[] addressDetails) {
		contactAddresses.add(new Address(addressDetails));
	}

	/**
	 * Replaces the details of an address in a Contact instance.
	 * 
	 * @param addressIndex
	 *            The index of the desired address. Expects a value within the
	 *            range 0 to getTotalAddresses()-1.
	 * 
	 * @param addressDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void editAddress(int addressIndex, String[] addressDetails) {
		this.contactAddresses.get(addressIndex).editAddress(addressDetails);
	}

	/**
	 * Deletes a specific address in a Contact instance.
	 * 
	 * @param addressIndex
	 *            The index of the desired address. Expects a value within the
	 *            range 0 to getTotalAddresses()-1.
	 */
	public void removeAddress(int addressIndex) {
		this.contactAddresses.remove(addressIndex);
	}

	// Overrides

	/**
	 * Compares this instance of a Contact with another by the LAST_NAME field.
	 * 
	 * @param another
	 *            Another instance of a Contact to be compared with.
	 * 
	 * @return 0 if the strings are equal, a negative integer if this string is
	 *         before the specified string, or a positive integer if this string
	 *         is after the specified string.
	 */
	@Override
	public int compareTo(Contact another) {
		if (another == null)
			throw new IllegalArgumentException(
					"Cannot compare to a null object.");
		return this.getSpecificDetail(LAST_NAME).compareToIgnoreCase(
				another.getSpecificDetail(LAST_NAME));
	}

	/**
	 * @return The full name of a Contact instance (FIRST_NAME LAST_NAME).
	 */
	@Override
	public String toString() {
		return this.getSpecificDetail(FIRST_NAME) + " "
				+ this.getSpecificDetail(LAST_NAME);
	}

	// Address Internal Class

	/**
	 * Address is a basic data structure that stores address details in an
	 * array.
	 * 
	 * <h4>Description</h4>
	 * 
	 * This is an implementation of an data type which handles specific
	 * information for each instance created, provided that the array with whom
	 * it was initialized is unique (not a reference to a previously provided
	 * array).
	 * 
	 */
	public class Address implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7811511022909771289L;
		/**
		 * Represents the maximum amount of fields each instance of a Address
		 * can support. To be used during initialization of the string array to
		 * be passed during initialization of each Contact instance.
		 */
		public static final int TOTAL_ADDRESS_DETAILS = 6;
		/**
		 * Retrieves the first index in the array. To be used in conjunction
		 * with the getSpecificDetail Method.
		 */
		public static final int ADDRESS_NAME = 0;
		/**
		 * Retrieves the first index in the array. To be used in conjunction
		 * with the getSpecificDetail Method.
		 */
		public static final int ADDRESS_STREET = 1;
		/**
		 * Retrieves the second index in the array. To be used in conjunction
		 * with the getSpecificDetail Method.
		 */
		public static final int ADDRESS_NUMBER = 2;
		/**
		 * Retrieves the third index in the array. To be used in conjunction
		 * with the getSpecificDetail Method.
		 */
		public static final int ADDRESS_CITY = 3;
		/**
		 * Retrieves the fourth index in the array. To be used in conjunction
		 * with the getSpecificDetail Method.
		 */
		public static final int ADDRESS_STATE = 4;
		/**
		 * Retrieves the fifth index in the array. To be used in conjunction
		 * with the getSpecificDetail Method.
		 */
		public static final int ADDRESS_ZIP_CODE = 5;

		// Instance variables
		private String[] addressDetails = new String[TOTAL_ADDRESS_DETAILS];

		// Constructors
		/**
		 * Creates an address with a default initialization.
		 * 
		 * @param addressDetails
		 *            A String Array using the format established in the static
		 *            fields.
		 */
		public Address(String[] addressDetails) {
			this.addressDetails = addressDetails;
		}

		// Getters
		/**
		 * @return A String Array with the details of an Address instance.
		 */
		public String[] getAddressDetails() {
			return this.addressDetails;
		}

		/**
		 * @return A specific detail of an Address instance.
		 * 
		 * @param fieldIndex
		 *            The index of the desired specific detail. Should use the
		 *            static fields provided by the class for this method.
		 *            Expects a value within the range 0 to
		 *            TOTAL_ADDRESS_DETAILS.
		 */
		public String getAddressSpecificDetail(int fieldIndex) {
			return this.addressDetails[fieldIndex];
		}

		// Setters
		/**
		 * Replaces the details of an Address instance.
		 * 
		 * @param addressDetails
		 *            A String Array using the format established in the static
		 *            fields.
		 */
		public void editAddress(String[] addressDetails) {
			this.addressDetails = addressDetails;
		}
	}
}
