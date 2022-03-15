package com.buzzware.apnigaridriver.activities.yourTrip.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buzzware.apnigaridriver.activities.home.Home;
import com.buzzware.apnigaridriver.databinding.ItemRideBinding;
import com.buzzware.apnigaridriver.databinding.ItemRideUpcommingBinding;

public class YourTripAdapterUpComming extends RecyclerView.Adapter<YourTripAdapterUpComming.ViewHolder> {

    Context c;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (c == null)
            c = parent.getContext();

        ItemRideUpcommingBinding binding = ItemRideUpcommingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.getRoot().setOnClickListener(view -> {

            c.startActivity(new Intent(c, Home.class));

        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ItemRideUpcommingBinding binding;

        public ViewHolder(@NonNull ItemRideUpcommingBinding binding) {

            super(binding.getRoot());

            this.binding = binding;
        }
    }
}
