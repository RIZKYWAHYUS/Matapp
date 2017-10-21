package com.example.user.matapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DataLeaderboard extends RecyclerView.Adapter<DataLeaderboard.ViewHolder> {
    private ArrayList<String> countries;
    private ArrayList gambar;
    private ArrayList<String> no;
    private ArrayList<String> lcertificate;
    private ArrayList<String> lmedal;
    private ArrayList<String> lcoins;

    public DataLeaderboard(ArrayList<String> countries, ArrayList gambar, ArrayList<String> no, ArrayList<String> lcertificate, ArrayList<String> lmedal, ArrayList<String> lcoins) {
        this.gambar = gambar;
        this.countries = countries;
        this.no = no;
        this.lcertificate = lcertificate;
        this.lmedal = lmedal;
        this.lcoins = lcoins;
    }

    @Override
    public DataLeaderboard.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_leaderboad, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataLeaderboard.ViewHolder viewHolder, int i) {
        viewHolder.iv_gambar.setImageResource((int) gambar.get(i));
        viewHolder.tv_country.setText(countries.get(i));
        viewHolder.no.setText(no.get(i));
        viewHolder.lcertificate.setText(lcertificate.get(i));
        viewHolder.lmedal.setText(lmedal.get(i));
        viewHolder.lcoins.setText(lcoins.get(i));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        private TextView no;
        private TextView lcertificate;
        private TextView lmedal;
        private TextView lcoins;
        private ImageView iv_gambar;

        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView)view.findViewById(R.id.teksMenu);
            iv_gambar = (ImageView) view.findViewById(R.id.gambarIcon);
            lcertificate = (TextView)view.findViewById(R.id.label_certificate);
            no = (TextView)view.findViewById(R.id.no);
            lmedal = (TextView)view.findViewById(R.id.label_medal);
            lcoins = (TextView)view.findViewById(R.id.label_coin);
        }
    }

}