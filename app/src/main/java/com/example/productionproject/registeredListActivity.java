package com.example.productionproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

import java.util.ArrayList;

public class registeredListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;
    ContactDetailAdapter contactDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_list);
        firebaseDatabase=FirebaseDatabase.getInstance();

        recyclerView= (RecyclerView)findViewById(R.id.recycleViewForRegistered);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ContactDetails> options =
                new FirebaseRecyclerOptions.Builder<ContactDetails>()
                        .setQuery( firebaseDatabase.getReference("productionproject"), ContactDetails.class)
                        .build();

        contactDetailAdapter =new ContactDetailAdapter(options);
        recyclerView.setAdapter(contactDetailAdapter);

    }
    @Override
    protected void onStart() {
        super.onStart();
        contactDetailAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        contactDetailAdapter.stopListening();
    }

}