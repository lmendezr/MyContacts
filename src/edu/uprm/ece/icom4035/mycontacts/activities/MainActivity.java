package edu.uprm.ece.icom4035.mycontacts.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import edu.uprm.ece.icom4035.mycontacts.adapters.MainListAdapter;
import edu.uprm.ece.icom4035.mycontacts.managers.ContactsManager;
import edu.uprm.edu.icom4035.mycontacts.R;

public class MainActivity extends Activity {

	public static final String EXTRA_MESSAGE = "edu.uprm.ece.icom4015.mycontact.MESSAGE";

	private ContactsManager contactsManager;
	private ListView listView;
	private MainListAdapter adapter;
	private EditText inputSearch;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		populateDetails();
	}

	public void populateDetails() {
		contactsManager = new ContactsManager();

		listView = (ListView) findViewById(R.id.contacts_list);

		adapter = new MainListAdapter(this, contactsManager.getAll());
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				int contactPosition = contactsManager
						.getContactPosition((int) adapter.getItemId(position));
				startViewContact(contactPosition);
			}
		});
	}

	public void filterDetails() {

		/**
		 * Enabling Search Filter
		 * */
		inputSearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				adapter.getFilter().filter(cs);
				if (inputSearch.length() == 0) {
					populateDetails();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}
		});

	}

	public void startViewContact(int contactIndex) {
		Intent intent = new Intent(this, ViewContact.class);
		String message = ((Integer) contactIndex).toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}

	public void deleteAllContacts() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.dialog_title_delete).setMessage(
				R.string.dialog_message_delete_all_contacts);
		builder.setNegativeButton(R.string.dialog_deny,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		builder.setPositiveButton(R.string.dialog_affirm,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						contactsManager.clearContacts();
						Toast.makeText(getApplicationContext(),
								R.string.toast_all_contacts_deleted,
								Toast.LENGTH_SHORT).show();
						adapter.notifyDataSetChanged();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();

	}

	@Override
	protected void onResume() {
		populateDetails();
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);

		MenuItem searchItem = menu.findItem(R.id.search);
		SearchView searchView = (SearchView) MenuItemCompat
				.getActionView(searchItem);

		int id = searchView.getContext().getResources()
				.getIdentifier("android:id/search_src_text", null, null);
		inputSearch = (EditText) searchView.findViewById(id);
		filterDetails();

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			startActivity(new Intent(getApplicationContext(), AddContact.class));
			return true;
		case R.id.action_delete_all:
			deleteAllContacts();
			return true;
		}

		return super.onOptionsItemSelected(item);

	}
}