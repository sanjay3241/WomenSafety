package com.example.productionproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class _profile extends Fragment {

    FirebaseAuth mAuth;
    Button btnLogout;
    EditText txtemail,txtNamr,txtNumber;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtemail=view.findViewById(R.id.txtemail);
        txtNamr=view.findViewById(R.id.txtname);
        txtNumber=view.findViewById(R.id.txtnumber);

        mAuth=FirebaseAuth.getInstance();
        btnLogout=view.findViewById(R.id.btn_logout);

        user=mAuth.getCurrentUser();
        if(user==null){
            Intent intent= new Intent(getActivity(),Login.class);
            startActivity(intent);
        }
        else{
            txtemail.setText(user.getEmail());
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(getActivity(),Login.class);
                startActivity(intent);
            }
        });

        return view;
    }
}