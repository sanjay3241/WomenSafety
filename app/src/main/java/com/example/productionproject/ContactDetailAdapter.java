package com.example.productionproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ContactDetailAdapter extends FirebaseRecyclerAdapter<ContactDetails,ContactDetailAdapter.ViewHolder> {

    public ContactDetailAdapter(@NonNull FirebaseRecyclerOptions<ContactDetails> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ContactDetails model) {
        holder.singleNumber.setText(model.getNumber());
        holder.singleName.setText(model.getName());

        holder.btnDelete.setOnClickListener((view) -> {
            AlertDialog.Builder builder= new AlertDialog.Builder(holder.singleName.getContext());
            builder.setTitle("Delete");
            builder.setMessage("Delete Entry");

            builder.setPositiveButton("yes",(dialogInterface, i) -> {
                FirebaseDatabase.getInstance().getReference().child("productionproject")
                        .child(getRef(position).getKey()).removeValue();
            });
            builder.setNegativeButton("No",(dialogInterface, i) -> {
            });
            builder.show();
        });

        holder.btnCall.setOnClickListener((view) -> {
            Intent intent= new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + model.getNumber()));
            holder.singleNumber.getContext().startActivity(intent);
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registeredlistitem,parent,false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView singleName , singleNumber;
        ImageView btnDelete,btnCall;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            singleName=itemView.findViewById(R.id.idSingleNumber);
            singleNumber=itemView.findViewById(R.id.idSingleName);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            btnCall=itemView.findViewById(R.id.btnCall);
        }
    }

}
