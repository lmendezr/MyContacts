package edu.uprm.ece.icom4035.mycontacts.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import edu.uprm.ece.icom4035.mycontacts.managers.Contact;
import edu.uprm.ece.icom4035.mycontacts.managers.ContactsManager;
import edu.uprm.edu.icom4035.mycontacts.R;

public class AddContact extends Activity {

	private ContactsManager contactsManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_contact);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	public void saveContact() {
		contactsManager = new ContactsManager();
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
			contactsManager.addNewContact(contactDetails);

			Toast.makeText(getApplicationContext(),
					R.string.toast_contact_saved, Toast.LENGTH_SHORT).show();

			finish();
		}
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
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_accept:
			saveContact();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
