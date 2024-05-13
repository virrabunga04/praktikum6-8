package com.example.praktikum6.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Entity
public class Resep implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;

    @ColumnInfo(name = "nama")
    public String nama;

    @ColumnInfo(name = "desc")
    public String desc;

    @ColumnInfo(name = "resep")
    public Bitmap resep;

    @ColumnInfo(name = "bahan")
    public List<String> bahan;

    @ColumnInfo(name = "langkah")
    public List<String> langkah;

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Bitmap getResep() {
        return resep;
    }

    public void setResep(Bitmap resep) {
        this.resep = resep;
    }

    public List<String> getBahan() {
        return bahan;
    }

    public void setBahan(List<String> bahan) {
        this.bahan = bahan;
    }

    public List<String> getLangkah() {
        return langkah;
    }

    public void setLangkah(List<String> langkah) {
        this.langkah = langkah;
    }

    // Tambahkan implementasi untuk metode getFormattedBahan() dan getFormattedLangkah()
    public String getFormattedBahan() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : bahan) {
            stringBuilder.append(item).append("\n");
        }
        return stringBuilder.toString();
    }

    public String getFormattedLangkah() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String item : langkah) {
            stringBuilder.append(item).append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    private byte[] bitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nama);
        dest.writeString(desc);
        dest.writeStringList(bahan);
        dest.writeStringList(langkah);

        // Menyimpan gambar ke parcel sebagai byte array
        byte[] bitmapData = bitmapToByteArray(resep);
        dest.writeInt(bitmapData.length);
        dest.writeByteArray(bitmapData);
    }

    @Ignore
    public Resep() {
    }
    public Resep(Bitmap resep, String nama, String desc, List<String> bahan, List<String> langkah) {
        this.nama = nama;
        this.desc = desc;
        this.bahan = bahan;
        this.langkah = langkah;
        this.resep = resep;
    }
    protected Resep(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        desc = in.readString();
        bahan = in.createStringArrayList();
        langkah = in.createStringArrayList();

        // Membaca byte array dari parcel dan membuat bitmap
        int bitmapDataLength = in.readInt();
        byte[] bitmapData = new byte[bitmapDataLength];
        in.readByteArray(bitmapData);
        resep = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapDataLength);
    }

    public static final Parcelable.Creator<Resep> CREATOR = new
            Parcelable.Creator<Resep>() {
                @Override
                public Resep createFromParcel(Parcel in) {
                    return new Resep(in);
                }

                @Override
                public Resep[] newArray(int size) {
                    return new Resep[size];
                }
            };
}