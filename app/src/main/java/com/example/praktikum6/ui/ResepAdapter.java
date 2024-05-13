package com.example.praktikum6.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.praktikum6.databinding.ResepRowBinding;
import com.example.praktikum6.database.Resep;
import com.example.praktikum6.helper.ResepDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class ResepAdapter extends RecyclerView.Adapter<ResepAdapter.ResepViewHolder> {
    private ArrayList<Resep> reseps = new ArrayList<>();
    private Context context;

    void setReseps(List<Resep> reseps) {
        final ResepDiffCallback diffCallback = new ResepDiffCallback(this.reseps, reseps);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.reseps.clear();
        this.reseps.addAll(reseps);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ResepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ResepRowBinding binding = ResepRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ResepViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ResepViewHolder holder, int position) {
        holder.bind(reseps.get(position));
    }

    @Override
    public int getItemCount() {
        return reseps.size();
    }

    static class ResepViewHolder extends RecyclerView.ViewHolder {
        final ResepRowBinding binding;

        ResepViewHolder(ResepRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Resep resep) {
            binding.tvNamaResep.setText(resep.getNama());
            binding.tvDeskripsi.setText(resep.getDesc());
            binding.ivResep.setImageBitmap(resep.getResep());
            binding.btTampil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Resep resep = reseps.get(position);
                        Popup.showPopup(v.getContext(), v, resep); // Panggil metode showPopup dari kelas popup
                    }
                }
            });
            binding.ibClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        removeItem(position);
                        Toast.makeText(binding.getRoot().getContext(), "Resep berhasil dihapus", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            binding.ibedit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(binding.getRoot().getContext(), TambahResep.class);
                        intent.putExtra("resep", reseps.get(position));
                        binding.getRoot().getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
