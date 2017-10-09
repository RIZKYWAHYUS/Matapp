package com.example.user.matapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<String> countries;
    private ArrayList gambar;

    public DataAdapter(ArrayList<String> countries, ArrayList gambar) {
        this.gambar = gambar;
        this.countries = countries;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {
        viewHolder.iv_gambar.setImageResource((int) gambar.get(i));
        viewHolder.tv_country.setText(countries.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        private ImageView iv_gambar;
        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView)view.findViewById(R.id.teksMenu);
            iv_gambar = (ImageView) view.findViewById(R.id.gambarIcon);
        }
    }

}