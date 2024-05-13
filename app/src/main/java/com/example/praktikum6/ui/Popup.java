package com.example.praktikum6.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;

import com.example.praktikum6.R;
import com.example.praktikum6.database.Resep;
import com.example.praktikum6.databinding.ActivityPopupBinding;

public class Popup extends AppCompatActivity {
    public static void showPopup(Context context, View anchorView, Resep resep) {
        ActivityPopupBinding binding = ActivityPopupBinding.inflate(LayoutInflater.from(context));
        View popupView = binding.getRoot();
        binding.tvNama.setText(resep.getNama());
        binding.tvDesc.setText(resep.getDesc());
        binding.tvBahan.setText(resep.getFormattedBahan());
        binding.tvLangkah.setText(resep.getFormattedLangkah());

        // Menginisialisasi tombol tutup
        Button btTutup = popupView.findViewById(R.id.btTutup);

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;

        // Menghitung 80% dari lebar dan tinggi layar
        int popupWidth = (int) (screenWidth * 0.8);
        int popupHeight = (int) (screenHeight * 0.8);

        PopupWindow popupWindow = new PopupWindow(popupView, popupWidth, popupHeight);
        popupWindow.setFocusable(true);

        // Menambahkan onClickListener untuk tombol tutup
        btTutup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // Menampilkan popup window di tengah layar
        popupWindow.showAtLocation(anchorView, Gravity.CENTER, 0, 0);
    }
}