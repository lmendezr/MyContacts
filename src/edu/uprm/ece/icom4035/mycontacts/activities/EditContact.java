package edu.uprm.ece.icom4035.mycontacts.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import edu.uprm.ece.icom4035.mycontacts.adapters.SimpleAddressListAdapter;
import edu.uprm.ece.icom4035.mycontacts.managers.Contact;
import edu.uprm.ece.icom4035.mycontacts.managers.ContactsManager;
import edu.uprm.edu.icom4035.mycontacts.R;

public class EditContact extends Activity {

	public static final String EXTRA_MESSAGE = "edu.uprm.ece.icom4015.mycontact.MESSAGE";
	public static final String EXTRA_MESSAGE2 = "edu.uprm.ece.icom4015.mycontact.MESSAGE2";
	public static final String EXTRA_Contact = "edu.uprm.ece.icom4015.mycontact.CONTACT";

	private ContactsManager contactsManager;
	private ListView listView;
	private SimpleAddressListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_contact);
		// Show the Up button in the action bar.
		setupActionBar();

		populateDetails();
		populateAddresses();
	}

	public int getContactIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				ViewContact.EXTRA_MESSAGE));
	}

	public void populateDetails() {
		contactsManager = new ContactsManager();
		TextView textView = new TextView(this);

		Contact currentContact = contactsManager.getAll()
				.get(getContactIndex());

		textView = (TextView) findViewById(R.id.edit_first_name_field);
		textView.setText(currentContact.getSpecificDetail(Contact.FIRST_NAME));

		textView = (TextView) findViewById(R.id.edit_last_name_field);
		textView.setText(currentContact.getSpecificDetail(Contact.LAST_NAME));

		textView = (TextView) findViewById(R.id.edit_cell_phone_field);
		textView.setText(currentContact.getSpecificDetail(Contact.CELL_PHONE));

		textView = (TextView) findViewById(R.id.edit_work_phone_field);
		textView.setText(currentContact.getSpecificDetail(Contact.WORK_PHONE));

		textView = (TextView) findViewById(R.id.edit_email_field);
		textView.setText(currentContact.getSpecificDetail(Contact.EMAIL));
	}

	public void populateAddresses() {
		contactsManager = new ContactsManager();
		listView = (ListView) findViewById(R.id.edit_addresses_list);
		adapter = new SimpleAddressListAdapter(this, contactsManager.getAll()
				.get(getContactIndex()).getAddresses());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				editAddress(getContactIndex(), position);
			}
		});
	}

	public void saveContact() {
		String[] contactDetails = new String[Contact.TOTAL_DETAILS];
		EditText firstName = (EditText) findViewById(R.id.edit_first_name_field);
		contactDetails[Contact.FIRST_NAME] = firstName.getText().toString();

		EditText lastName = (EditText) findViewById(R.id.edit_last_name_field);
		contactDetails[Contact.LAST_NAME] = lastName.getText().toString();

		EditText cellPhone = (EditText) findViewById(R.id.edit_cell_phone_field);
		contactDetails[Contact.CELL_PHONE] = cellPhone.getText().toString();

		EditText workPhone = (EditText) findViewById(R.id.edit_work_phone_field);
		contactDetails[Contact.WORK_PHONE] = workPhone.getText().toString();

		EditText email = (EditText) findViewById(R.id.edit_email_field);
		contactDetails[Contact.EMAIL] = email.getText().toString();

		if (firstName.getText().toString().isEmpty()) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.dialog_title_save_contact);
			builder.setMessage(R.string.dialog_message_save_contact);
			builder.setNeutralButton(R.string.dialog_confirm,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			contactsManager.editContact(getContactIndex(), contactDetails);
			int contactPosition = contactsManager
					.getContactPosition((int) adapter.getItemId(getContactIndex()));
			Toast.makeText(getApplicationContext(),
					R.string.toast_contact_saved, Toast.LENGTH_SHORT).show();
			startViewContact(contactPosition);
			setResult(1);
			finish();
		}
	}

	public void addAddress(View view) {
		Intent intent = new Intent(this, AddAddress.class);
		String message = ((Integer) getContactIndex()).toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void editAddress(int contactIndex, int addressIndex) {
		Intent intent = new Intent(this, EditAddress.class);
		String message = ((Integer) contactIndex).toString();
		String message2 = ((Integer) addressIndex).toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		intent.putExtra(EXTRA_MESSAGE2, message2);
		startActivity(intent);
	}

	public void deleteContact(View view) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dialog_title_delete);
		builder.setMessage(R.string.dialog_message_delete_contact);
		builder.setNegativeButton(R.string.dialog_deny,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.setPositiveButton(R.string.dialog_affirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						contactsManager.removeContact(getContactIndex());
						Toast.makeText(getApplicationContext(),
								R.string.toast_contact_deleted,
								Toast.LENGTH_SHORT).show();
						setResult(1);
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}
	
	public void startViewContact(int contactIndex) {
		Intent intent = new Intent(this, ViewContact.class);
		String message = ((Integer) contactIndex).toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	@Override
	protected void onResume() {
		populateAddresses();
		super.onResume();
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.action_accept:
			saveContact();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
