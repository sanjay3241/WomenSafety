package com.example.productionproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.jar.Attributes;

public class addyournumber extends Fragment {

    View view;
    Button btnSave;
    EditText txtName,txtNumber;

    BottomNavigationView bottomView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_addyournumber, container, false);
         btnSave=view.findViewById(R.id.btn_SaveMyNumber);
         txtName=view.findViewById(R.id.myName);
         txtNumber=view.findViewById(R.id.myNumber);

         firebaseDatabase=FirebaseDatabase.getInstance();
         databaseReference=firebaseDatabase.getReference("productionproject");

         btnSave.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String FullName=txtName.getText().toString();
                 String MNumber=txtNumber.getText().toString();
                 String myId=txtName.getText().toString();

                 ContactDetails contactDetail=new ContactDetails(myId,FullName,MNumber,"My");

                 databaseReference.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot snapshot) {
                         databaseReference.child(myId).setValue(contactDetail);
                         Toast.makeText(getActivity() , "Number Added", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(getActivity(), MainActivity.class);
                         bottomView=((MainActivity)getActivity()).findViewById(R.id.bottomNavigationView);
                         bottomView.setVisibility(View.VISIBLE);
                         startActivity(intent);
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError error) {
                         Toast.makeText(getActivity(), "Error Is"+error.toString(), Toast.LENGTH_SHORT).show();
                     }
                 });

             }
         });
         return view;
    }
}