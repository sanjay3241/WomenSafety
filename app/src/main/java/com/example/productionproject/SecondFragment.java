package com.example.productionproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    Button addNumber,addGuardianNumber,ViewRegistered,btnInDanger;
    TextView locationTv;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);



        locationTv=view.findViewById(R.id.idtxtLocation);
        locationTv.setText(""+((MainActivity) getActivity()).GetLocation()+"");

        addNumber=view.findViewById(R.id.btn_addYourself);
        addNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragmentAddMyNumber();
            }
        });
        addGuardianNumber=view.findViewById(R.id.btn_addGuardian);
        addGuardianNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).replaceFragmentAddGuardianNumber();
            }
        });
        ViewRegistered=view.findViewById(R.id.btn_viewRegistered);
        ViewRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), registeredListActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnInDanger=view.findViewById(R.id.btn_inDanger);
        btnInDanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).openMapWithAddress();
            }
        });

        return view;
    }
}