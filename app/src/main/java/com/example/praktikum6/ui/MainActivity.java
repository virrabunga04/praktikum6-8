package com.example.praktikum6.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.praktikum6.R;
import com.example.praktikum6.databinding.ActivityMainBinding;
import com.example.praktikum6.helper.ViewModelFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ResepAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ResepMainViewModel noteMainViewModel = obtainViewModel(MainActivity.this);
        noteMainViewModel.getAllReseps().observe(this, reseps -> {
            if (reseps != null) {adapter.setReseps(reseps);
            }
        });
        adapter = new ResepAdapter();
        binding.rvResep.setLayoutManager(new LinearLayoutManager(this));
        binding.rvResep.setHasFixedSize(true);
        binding.rvResep.setAdapter(adapter);
        binding.ibTambah.setOnClickListener(view -> {
            if (view.getId() == R.id.ibTambah) {
                Intent intent = new Intent(MainActivity.this, TambahResep.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
    @NonNull
    private static ResepMainViewModel
    obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(ResepMainViewModel.class);
    }
}
