package com.example.admin.olayscontactapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateContactFragment extends Fragment {

    public static final int CAMERA_REQUEST = 1888;

    ImageView ivContactImage;
    EditText etFirstName;
    EditText etLastName;
    EditText etCompany;
    EditText etPhone;
    EditText etEmail;
    Button btnSave;
    Button btnCancel;
    Contact contact;
    ArrayList<Contact> contacts;


    public CreateContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_contact, container, false);

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etCompany = view.findViewById(R.id.etCompany);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        ivContactImage = view.findViewById(R.id.ivContactImage);
        ivContactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        contacts = new ArrayList<>();
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    contact = new Contact(
                            etFirstName.getText().toString().trim(), etLastName.getText().toString().trim(),
                            Integer.valueOf(etPhone.getText().toString()), etCompany.getText().toString().trim(),
                            imageViewToByte());
                    DatabaseHelper db = new DatabaseHelper(CreateContactFragment.this.getActivity());
                    db.saveNewContact(contact);
                    Toast.makeText(CreateContactFragment.this.getActivity(), "Sucessfully Added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(CreateContactFragment.this.getActivity(), MainActivity.class);
                    startActivity(intent);

                } catch (NumberFormatException e){
                    Toast.makeText(CreateContactFragment.this.getActivity(),
                            "Incorrect Phone Number Format", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel = view.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateContactFragment.this.getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private byte[] imageViewToByte() {
        Bitmap bitmap = ((BitmapDrawable) ivContactImage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] array = stream.toByteArray();
        return array;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap picture = (Bitmap) data.getExtras().get("data");
        ivContactImage.setImageBitmap(picture);
    }

}
