package com.example.admin.olayscontactapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditContactFragment extends Fragment {

    EditText etFName, etLName, etPhoneN, etComp;
    ImageView ivImageEdit;
    Button btnSaveEdit, btnDelete;

    public EditContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        Contact contact = getArguments().getParcelable("CURRENT_CONTACT");

/*
        DatabaseHelper databaseHelper = new DatabaseHelper(EditContactFragment.this.getActivity());
        Contact contact = databaseHelper.getOneContact(position);
*/

        etFName = view.findViewById(R.id.etFName);
        etFName.setText(contact.getFirstName());
        etLName = view.findViewById(R.id.etLName);
        etLName.setText(contact.getLastName());
        etPhoneN = view.findViewById(R.id.etPhoneN);
        etPhoneN.setText(contact.getPhoneNumber());
        etComp = view.findViewById(R.id.etComp);
        etComp.setText(contact.getCompany());

        ivImageEdit = view.findViewById(R.id.ivImageEdit);
        byte[] contactPhoto = contact.getPhoto();
        Bitmap bitmap = BitmapFactory.decodeByteArray(contactPhoto, 0, contactPhoto.length);
        ivImageEdit.setImageBitmap(bitmap);

        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnSaveEdit = view.findViewById(R.id.btnSaveEdit);
        btnSaveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}
