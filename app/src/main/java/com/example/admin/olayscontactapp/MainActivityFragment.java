package com.example.admin.olayscontactapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayList<Contact> contacts = new ArrayList<>();
    ListView lvContactList;
    ArrayList<AdapterItems> listContacts = new ArrayList<>();
    MyCustomAdapter adapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        lvContactList = view.findViewById(R.id.lvContactList);

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivityFragment.this.getActivity());
        contacts = databaseHelper.getContact();
        for(int i = 0; i < contacts.size(); i++){
            listContacts.add(new AdapterItems(
                    contacts.get(i).firstName + " " + contacts.get(i).lastName,
                    contacts.get(i).phoneNumber, contacts.get(i).photo));
        }

        adapter = new MyCustomAdapter(listContacts);
        lvContactList.setAdapter(adapter);
        lvContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                EditContactFragment editContactFragment = new EditContactFragment();
/*
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
*/
                Bundle bundle = new Bundle();
                bundle.putParcelable("CURRENT_CONTACT", contacts.get(position));
                editContactFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().add(R.id.fragment, editContactFragment).commit();
            }
        });

        return view;
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater ;

        public MyCustomAdapter(ArrayList<AdapterItems>  listnewsDataAdpater) {
            this.listnewsDataAdpater=listnewsDataAdpater;
        }


        @Override
        public int getCount() {
            return listnewsDataAdpater.size();
        }

        @Override
        public String getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater mInflater = getActivity().getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_listview_contact, null);

            Contact contact;

                contact = contacts.get(position);


            final AdapterItems s = listnewsDataAdpater.get(position);

            TextView tvFullName = myView.findViewById(R.id.tvFullName);
            tvFullName.setText(s.FullName);
            TextView tvPhoneNumber = myView.findViewById(R.id.tvPhoneNumber);
            tvPhoneNumber.setText(s.PhoneNumber);
            ImageView ivShowContactImage = myView.findViewById(R.id.ivShowContactImage);
            byte[] contactPhoto = contact.getPhoto();
            Bitmap bitmap = BitmapFactory.decodeByteArray(contactPhoto, 0, contactPhoto.length);
            ivShowContactImage.setImageBitmap(bitmap);
            //ivShowContactImage.setImageBitmap(null);

            return myView;
        }

    }

}
