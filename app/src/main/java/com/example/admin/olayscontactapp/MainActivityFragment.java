package com.example.admin.olayscontactapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ArrayList<Contact> contacts = new ArrayList<>();
    //ListView lvContactList;
    ArrayList<AdapterItems> listContacts = new ArrayList<>();
    MyCustomAdapter adapter;

    SwipeMenuListView lvContactList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        lvContactList = view.findViewById(R.id.lvContactList);

        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivityFragment.this.getActivity());
        contacts = databaseHelper.getContact();
        for (int i = 0; i < contacts.size(); i++) {
            listContacts.add(new AdapterItems(
                    contacts.get(i).firstName + " " + contacts.get(i).lastName,
                    contacts.get(i).phoneNumber, contacts.get(i).photo));
        }

        adapter = new MyCustomAdapter(listContacts);
        lvContactList.setAdapter(adapter);
/*
        lvContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });
*/


        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "edit" item
                SwipeMenuItem editItem = new SwipeMenuItem(
                        MainActivityFragment.this.getActivity());
                // set item background
                editItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                editItem.setWidth(170);
                // set item title
                editItem.setTitle("Edit");
                // set item title fontsize
                editItem.setTitleSize(18);
                // set item title font color
                editItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(editItem);

                // create "call" item
                SwipeMenuItem callItem = new SwipeMenuItem(
                        MainActivityFragment.this.getActivity());
                // set item background
                callItem.setBackground(new ColorDrawable(Color.rgb(0x5f, 0xf4,
                        0x42)));
                // set item width
                callItem.setWidth(170);
                // set a icon
                callItem.setIcon(R.drawable.ic_phone);
                // add to menu
                menu.addMenuItem(callItem);


                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        MainActivityFragment.this.getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(170);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        lvContactList.setMenuCreator(creator);

        lvContactList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        EditContactFragment editContactFragment = new EditContactFragment();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("CURRENT_CONTACT", contacts.get(position));
                        editContactFragment.setArguments(bundle);
                        getFragmentManager().beginTransaction().add(R.id.fragment, editContactFragment).addToBackStack(null).commit();
                        break;
                    case 1:
                        Toast.makeText(MainActivityFragment.this.getActivity(), "World", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(MainActivityFragment.this.getActivity(), "World", Toast.LENGTH_SHORT).show();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

        return view;
    }

    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdpater;

        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdpater) {
            this.listnewsDataAdpater = listnewsDataAdpater;
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
        public View getView(int position, View convertView, ViewGroup parent) {
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
