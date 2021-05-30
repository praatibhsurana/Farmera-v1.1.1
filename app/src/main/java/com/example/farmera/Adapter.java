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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<String> staten, distn, cyear, season, crop, area, prod;
    private LayoutInflater inflater;
    String areaa = "Area: ";
    String producc = "Produce: ";

    Adapter(Context context, List<String> staten, List<String> distn, List<String> cyear, List<String> season, List<String> crop, List<String> area, List<String> prod){
        //Log.d("data", "titles -> "+titles);
        this.staten = staten;
        this.distn = distn;
        this.cyear = cyear;
        this.season = season;
        this.crop = crop;
        this.area = area;
        this.prod = prod;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String desctitle = crop.get(position);
        String desc = staten.get(position);
        String desc2 = distn.get(position);
        String desc3 = season.get(position);
        String desc4 = area.get(position);
        String desc5 = prod.get(position);
        String descyear = cyear.get(position);

        holder.desctitle.setText(desctitle);
        holder.desc.setText(desc);
        holder.desc2.setText(desc2);
        holder.desc3.setText(desc3);
        holder.desc4.setText(areaa + desc4);
        holder.desc5.setText(producc + desc5);
        holder.descyear.setText(descyear);

    }

    @Override
    public int getItemCount() {
        return crop.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView desctitle,desc, desc2, desc3, desc4, desc5, descyear;
        ImageView thumbnail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            desctitle = itemView.findViewById(R.id.desctitle);
            desc = itemView.findViewById(R.id.desc);
            desc2 = itemView.findViewById(R.id.desc2);
            desc3 = itemView.findViewById(R.id.desc3);
            desc4 = itemView.findViewById(R.id.desc4);
            desc5 = itemView.findViewById(R.id.desc5);
            descyear = itemView.findViewById(R.id.descyear);


        }
    }
}