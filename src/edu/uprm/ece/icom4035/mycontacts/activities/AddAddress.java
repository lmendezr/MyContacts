package edu.uprm.ece.icom4035.mycontacts.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import edu.uprm.ece.icom4035.mycontacts.managers.Contact.Address;
import edu.uprm.ece.icom4035.mycontacts.managers.ContactsManager;
import edu.uprm.edu.icom4035.mycontacts.R;

public class AddAddress extends Activity {

	public static final String EXTRA_MESSAGE = "edu.uprm.ece.icom4015.mycontact.MESSAGE";

	private ContactsManager contactsManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_address);
		// Show the Up button in the action bar.
		setupActionBar();
	}

	public int getContactIndex() {
		return Integer.parseInt(getIntent().getStringExtra(
				EditContact.EXTRA_MESSAGE));
	}

	public void saveAddress() {
		contactsManager = new ContactsManager();
		String[] addressDetails = new String[Address.TOTAL_ADDRESS_DETAILS];
		EditText name = (EditText) findViewById(R.id.edit_address_name_field);
		addressDetails[Address.ADDRESS_NAME] = name.getText().toString();

		EditText street = (EditText) findViewById(R.id.edit_address_street_field);
		addressDetails[Address.ADDRESS_STREET] = street.getText().toString();

		EditText number = (EditText) findViewById(R.id.edit_address_number_field);
		addressDetails[Address.ADDRESS_NUMBER] = number.getText().toString();

		EditText city = (EditText) findViewById(R.id.edit_address_city_field);
		addressDetails[Address.ADDRESS_CITY] = city.getText().toString();

		EditText state = (EditText) findViewById(R.id.edit_address_state_field);
		addressDetails[Address.ADDRESS_STATE] = state.getText().toString();

		EditText zipCode = (EditText) findViewById(R.id.edit_address_zip_code_field);
		addressDetails[Address.ADDRESS_ZIP_CODE] = zipCode.getText().toString();

		if (name.getText().toString().isEmpty()) {

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.dialog_title_save_address);
			builder.setMessage(R.string.dialog_message_save_address);
			builder.setNeutralButton(R.string.dialog_confirm,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			AlertDialog alert = builder.create();
			alert.show();
		} else {
			contactsManager.addNewAddress(getContactIndex(), addressDetails);
			Toast.makeText(getApplicationContext(),
					R.string.toast_address_saved, Toast.LENGTH_SHORT).show();
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
			finish();
			return true;
		case R.id.action_accept:
			saveAddress();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
