package com.example.praktikum6.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.praktikum6.R;
import com.example.praktikum6.database.Resep;
import com.example.praktikum6.databinding.ActivityTambahResepBinding;
import com.example.praktikum6.helper.ViewModelFactory;

import java.io.IOException;
import java.util.ArrayList;

public class TambahResep extends AppCompatActivity {

    private ResepInsertUpdateViewModel resepInsertUpdateViewModel;
    private ActivityTambahResepBinding binding;
    private final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahResepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        resepInsertUpdateViewModel = obtainViewModel(this);
        binding.btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanResep();
            }
        });

        binding.btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btUnggahFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pilihGambar();
            }
        });
    }

    private void simpanResep() {
        String nama = binding.etNamaResep.getText().toString().trim();
        String desc = binding.etDeskripsi.getText().toString().trim();
        String bahan = binding.etbahan.getText().toString().trim();
        String langkah = binding.etLangkah.getText().toString().trim();

        if (nama.isEmpty() || desc.isEmpty() || bahan.isEmpty() || langkah.isEmpty()) {
            Toast.makeText(TambahResep.this, "Harap lengkapi semua form", Toast.LENGTH_SHORT).show();
        } else {
            ArrayList<String> bahanList = parseStringToList(bahan);
            ArrayList<String> langkahList = parseStringToList(langkah);
            Bitmap fotoResep = ((BitmapDrawable) binding.ivResep.getDrawable()).getBitmap();
            resepInsertUpdateViewModel.insert(new Resep(fotoResep, nama, desc, bahanList, langkahList));
            Toast.makeText(TambahResep.this, "Resep berhasil disimpan", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(TambahResep.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void pilihGambar() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    private ArrayList<String> parseStringToList(String input) {
        ArrayList<String> list = new ArrayList<>();
        String[] items = input.split(",");
        for (String item : items) {
            list.add(item.trim());
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                binding.ivResep.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal Memuat Gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @NonNull
    private ResepInsertUpdateViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(ResepInsertUpdateViewModel.class);
    }
}
