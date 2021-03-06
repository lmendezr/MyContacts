package edu.uprm.ece.icom4035.mycontacts.managers;

import java.io.IOException;

/**
 * ContactsManager is a bridge between the internal structure and the user
 * interface of MyContacts.
 * 
 * <h4>Description</h4>
 * 
 * ContactsManager is the class that bridges Android activities with everything
 * related with the Contacts. The main purpose of this class is to load contacts
 * from a file, store them in the SortedArrayList, perform modifications, and
 * update the file with the latest changes from the user.
 * 
 * @author Lixhjideny M�ndez R�os
 * 
 * @version 1.5
 * 
 */
public class ContactsManager {

	// Instance variables

	private SortedArrayList<Contact> contactsList = new SortedArrayList<Contact>();
	private ContactStore contactStorage = new ContactStore();

	// Constructors
	/**
	 * Creates an instance of ContactsManager, loading the Contacts from the
	 * file into the internal SortedArrayList.
	 */
	public ContactsManager() {
		try {
			this.contactsList = contactStorage.loadContacts();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Getters
	/**
	 * @return The internal SortedArrayList containing all the Contacts
	 *         currently loaded in the program.
	 */
	public SortedArrayList<Contact> getAll() {
		return contactsList;
	}

	/**
	 * Get the position of a contact in the SortedArrayList by providing it's
	 * unique ID.
	 * 
	 * @param ID
	 *            The unique identification of the desired Contact.
	 * 
	 * @return The position in the SortedArrayList of the desired Contact.
	 */
	public int getContactPosition(int ID) {
		for (int i = 0; i < contactsList.size(); ++i) {
			if (ID == contactsList.get(i).getID()) {
				return i;
			}
		}
		throw new IllegalArgumentException(
				"ID doesn't match any contacts in the list.");
	}

	// Setters
	/**
	 * Adds a new Contact into the internal SortedArrayList.
	 * 
	 * @param contactDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void addNewContact(String[] contactDetails) {
		contactsList.add(new Contact(contactDetails));
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds a new Contact into the internal SortedArrayList.
	 * 
	 * @param contact
	 *            A contact with all the desired details already provided.
	 */
	public void addNewContact(Contact contact) {
		contactsList.add(contact);
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a new address in a Contact stored in the internal SortedArrayList.
	 * 
	 * @param contactIndex
	 *            The index of the desired contact. Expects a value within the
	 *            range 0 to contactsList.size()-1.
	 * 
	 * @param addressDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void addNewAddress(int contactIndex, String[] addressDetails) {
		contactsList.get(contactIndex).addAddress(addressDetails);
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Replaces the details of a Contact stored in the internal SortedArrayList.
	 * 
	 * @param contactIndex
	 *            The index of the desired contact. Expects a value within the
	 *            range 0 to contactsList.size()-1.
	 * 
	 * @param contactDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void editContact(int contactIndex, String[] contactDetails) {
		contactsList.get(contactIndex).editContact(contactDetails);
		sortContacts();
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sorts the entire list of contacts after modifying a specific contact
	 * details. Avoids errors inside the SortedArrayList since sorting occurs
	 * only during addition of objects.
	 */
	public void sortContacts() {
		SortedArrayList<Contact> temp = new SortedArrayList<Contact>();
		for (Contact e : this.contactsList) {
			temp.add(e);
		}
		this.contactsList.clear();
		this.contactsList = temp;
	}

	/**
	 * Replaces the details of an Address in a Contact stored in the internal
	 * SortedArrayList.
	 * 
	 * @param contactIndex
	 *            The index of the desired contact. Expects a value within the
	 *            range 0 to contactsList.size()-1.
	 * 
	 * @param addressIndex
	 *            The index of the desired address. Expects a value within the
	 *            range 0 to contactsList.get(x).getTotalAddresses()-1.
	 * 
	 * @param contactDetails
	 *            A String Array using the format established in the static
	 *            fields.
	 */
	public void editAddress(int contactIndex, int addressIndex,
			String[] addressDetails) {
		contactsList.get(contactIndex)
				.editAddress(addressIndex, addressDetails);
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes Contact stored in the internal SortedArrayList.
	 * 
	 * @param contactIndex
	 *            The index of the desired contact. Expects a value within the
	 *            range 0 to contactsList.size()-1.
	 */
	public void removeContact(int contactIndex) {
		contactsList.remove(contactIndex);
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes an Address in a Contact stored in the internal SortedArrayList.
	 * 
	 * @param contactIndex
	 *            The index of the desired contact. Expects a value within the
	 *            range 0 to contactsList.size()-1.
	 * 
	 * @param addressIndex
	 *            The index of the desired address. Expects a value within the
	 *            range 0 to contactsList.get(x).getTotalAddresses()-1.
	 */
	public void removeAddress(int contactIndex, int addressIndex) {
		contactsList.get(contactIndex).removeAddress(addressIndex);
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clears all the Contacts stored in the internal SortedArrayList.
	 * 
	 * @return The amount of contacts cleared.
	 */
	public int clearContacts() {
		int amountCleared = this.contactsList.size();
		contactsList.clear();
		try {
			contactStorage.updateFile(contactsList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return amountCleared;
	}
}
