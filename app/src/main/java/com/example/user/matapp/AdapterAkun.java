package com.example.user.matapp;

/**
 * Created by SJ on 10/19/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterAkun extends RecyclerView.Adapter<AdapterAkun.ViewHolder> {
    private ArrayList<String> countries;
    private ArrayList<String> no;

    public AdapterAkun(ArrayList<String> countries, ArrayList<String> no) {
        this.countries = countries;
        this.no = no;
    }

    @Override
    public AdapterAkun.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_akun, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterAkun.ViewHolder viewHolder, int i) {
        viewHolder.tv_country.setText(countries.get(i));
        viewHolder.no.setText(no.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        private TextView no;

        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView)view.findViewById(R.id.teksMenu);
            no = (TextView)view.findViewById(R.id.teksMenu2);
        }
    }

}
