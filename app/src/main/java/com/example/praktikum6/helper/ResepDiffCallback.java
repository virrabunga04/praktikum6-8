package com.example.praktikum6.helper;

import androidx.recyclerview.widget.DiffUtil;

import com.example.praktikum6.database.Resep;

import java.util.List;

public class ResepDiffCallback extends DiffUtil.Callback {
    private final List<Resep> rOldResepList;
    private final List<Resep> rNewResepList;

    public ResepDiffCallback(List<Resep> oldResepList, List<Resep> newResepList){
        this.rOldResepList = oldResepList;
        this.rNewResepList = newResepList;
    }
    @Override
    public int getOldListSize(){
        return rOldResepList.size();
    }
    @Override
    public int getNewListSize(){
        return rNewResepList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition){
        return rOldResepList.get(oldItemPosition).getId() ==
                rNewResepList.get(newItemPosition).getId();
    }
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final Resep oldEmployee = rOldResepList.get(oldItemPosition);
        final Resep newEmployee = rNewResepList.get(newItemPosition);
        return oldEmployee.getNama().equals(newEmployee.getNama()) && oldEmployee.getDesc().equals(newEmployee.getDesc()) &&
                oldEmployee.getResep().equals(newEmployee.getResep());
    }
}
