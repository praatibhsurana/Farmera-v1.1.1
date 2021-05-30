package com.example.farmera;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolder2> {
    private List<String> pestn, pestg, p2017, p2018, p2019;
    private LayoutInflater inflater;
    String s7 = "2017: ";
    String s8 = "2018: ";
    String s9 = "2019: ";


    Adapter2(Context context, List<String> pestn, List<String> pestg, List<String> p2017, List<String> p2018, List<String> p2019){
        //Log.d("data", "titles -> "+titles);
        this.pestn = pestn;
        this.pestg = pestg;
        this.p2017 = p2017;
        this.p2018 = p2018;
        this.p2019 = p2019;

        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent2, int viewType2) {
        View view2 = inflater.inflate(R.layout.custom_layout2,parent2,false);
        return new ViewHolder2(view2);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter2.ViewHolder2 holder, int position) {
        String pesttitle = pestn.get(position);
        String pestgroup = pestg.get(position);
        String pest2017 = p2017.get(position);
        String pest2018 = p2018.get(position);
        String pest2019 = p2019.get(position);


        holder.pesttitle.setText(pesttitle);
        holder.pestgroup.setText(pestgroup);
        holder.pest2017.setText(s7 + pest2017);
        holder.pest2018.setText(s8  + pest2018);
        holder.pest2019.setText(s9 + pest2019);
    }


    @Override
    public int getItemCount() {
        return pestn.size();
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        TextView pesttitle, pestgroup, pest2017, pest2018, pest2019;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            pesttitle = itemView.findViewById(R.id.pesttitle);
            pestgroup = itemView.findViewById(R.id.pestgroup);
            pest2017 = itemView.findViewById(R.id.pest17);
            pest2018 = itemView.findViewById(R.id.pest18);
            pest2019 = itemView.findViewById(R.id.pest19);



        }
    }
}