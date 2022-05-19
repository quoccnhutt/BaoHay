package com.example.bohay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bohay.model.DanhMuc;

import java.util.List;

public class DanhMucAdapter extends BaseAdapter {

    Context context;
    List<DanhMuc> arrayListDanhmuc;


    public DanhMucAdapter(@NonNull Context context, List<DanhMuc> arrayListDanhmuc) {


        this.context = context;
        this.arrayListDanhmuc = arrayListDanhmuc;

    }

    @Override
    public int getCount() {
        return arrayListDanhmuc.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListDanhmuc.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.dong_danh_muc,null);

        TextView tvTendanhmuc = view.findViewById(R.id.tvTendanhmuc);
        ImageView iconDanhmuc  = view.findViewById(R.id.iconDanhmuc);

        tvTendanhmuc.setText(arrayListDanhmuc.get(position).getTendanhmuc());
        iconDanhmuc.setImageResource(arrayListDanhmuc.get(position).getImage());

        return view;
    }
}
