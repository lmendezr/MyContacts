package edu.uprm.ece.icom4035.mycontacts.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import edu.uprm.ece.icom4035.mycontacts.managers.Contact.Address;
import edu.uprm.edu.icom4035.mycontacts.R;

public class SimpleAddressListAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Address> mListAddresses;

	public SimpleAddressListAdapter(Context context, ArrayList<Address> list) {
		mContext = context;
		mListAddresses = list;
	}

	@Override
	public int getCount() {
		return mListAddresses.size();
	}

	@Override
	public Object getItem(int pos) {
		return mListAddresses.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View convertView, ViewGroup parent) {
		// get selected entry
		Address entry = mListAddresses.get(pos);

		// inflating list view layout if null
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater.inflate(R.layout.simple_address_row, null);
		}

		TextView textView = new TextView(mContext);

		// set name
		textView = (TextView) convertView
				.findViewById(R.id.display_address_name);
		textView.setText(entry.getAddressSpecificDetail(Address.ADDRESS_NAME));

		return convertView;
	}

}
