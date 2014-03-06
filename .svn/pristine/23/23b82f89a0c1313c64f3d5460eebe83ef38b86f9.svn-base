package edu.uprm.ece.icom4035.mycontacts.managers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import android.os.Environment;
import edu.uprm.ece.icom4035.mycontacts.managers.Contact.Address;

/**
 * ContactsManager is the file manager of MyContacts.
 * 
 * <h4>Description</h4>
 * 
 * ContactsStore provides support for storage in Android's external storage
 * directory. It's main purpose is to load Contacts from a text file into a
 * SortedArrayList and store Contacts from this SortedArrayList back to the same
 * text file.
 * 
 * @author Lixhjideny Méndez Ríos
 * 
 * @version 1.5
 * 
 */

public class ContactStore {

	// Instance variables

	public static final File cartridgeSlot = Environment
			.getExternalStorageDirectory();
	public static final File cartridge = new File(cartridgeSlot,
			"/MyContacts.txt");
	private int readContactsAmount = 0;

	// Dummy Constructor
	public ContactStore() {

	}

	/**
	 * Loads the Contacts found in the program's storage text file into a
	 * SortedArrayList.
	 * 
	 * @return A SortedArrayList containing Contacts.
	 */
	public SortedArrayList<Contact> loadContacts() throws IOException {
		PrintWriter out = null;
		if (!cartridge.exists()) {
			cartridge.createNewFile();
			out = new PrintWriter(cartridge);
			out.println(readContactsAmount);
			out.close();
			return new SortedArrayList<Contact>();
		}

		Scanner scanner = new Scanner(cartridge);
		if (!scanner.hasNext()) {
			out = new PrintWriter(cartridge);
			out.println(readContactsAmount);
			out.close();
			scanner.close();
			return new SortedArrayList<Contact>();
		}

		SortedArrayList<Contact> storedContacts;
		this.readContactsAmount = Integer.parseInt(scanner.nextLine());
		if (this.readContactsAmount < 10) {
			storedContacts = new SortedArrayList<Contact>();
		} else {
			storedContacts = new SortedArrayList<Contact>(
					this.readContactsAmount);
		}

		for (int i = 0; i < readContactsAmount; ++i) {
			String[] contactDetails = new String[Contact.TOTAL_DETAILS];
			for (int j = 0; j < Contact.TOTAL_DETAILS; ++j) {
				contactDetails[j] = scanner.nextLine();
			}
			Contact temp = new Contact(contactDetails, i);
			storedContacts.add(temp);

			int addressesAmount = Integer.parseInt(scanner.nextLine());
			for (int k = 0; k < addressesAmount; ++k) {
				String[] addressDetails = new String[Address.TOTAL_ADDRESS_DETAILS];
				for (int h = 0; h < Address.TOTAL_ADDRESS_DETAILS; ++h) {
					addressDetails[h] = scanner.nextLine();
				}
				temp.addAddress(addressDetails);
			}
		}
		scanner.close();
		return storedContacts;
	}

	// Setter
	/**
	 * Writes Contacts from a SortedArrayList to the program's storage text
	 * file.
	 * 
	 * @param contacts
	 *            A SortedArrayList containing Contacts.
	 */
	public void updateFile(SortedArrayList<Contact> contacts)
			throws IOException {
		if (contacts == null)
			throw new IllegalArgumentException(
					"The Sorted Array List shouldn't be null.");

		PrintWriter out = new PrintWriter(cartridge);
		out.println(contacts.size());
		for (int i = 0; i < contacts.size(); ++i) {
			for (int j = 0; j < Contact.TOTAL_DETAILS; ++j) {
				out.println(contacts.get(i).getSpecificDetail(j));
			}
			out.println(contacts.get(i).getTotalAddresses());
			for (int k = 0; k < contacts.get(i).getTotalAddresses(); ++k) {
				for (int h = 0; h < Address.TOTAL_ADDRESS_DETAILS; ++h) {
					out.println(contacts.get(i).getAddressSpecificDetail(k, h));
				}
			}
		}
		out.close();
	}
}
