package com.example.fyp.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fyp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {


    LayoutInflater inflater;
    List<Lists> lst;
    private ItemClickListsner mItemListener;


    public Adapter(Context ctx, List<Lists> lst, ItemClickListsner itemClickListsner) {
        this.inflater = LayoutInflater.from(ctx);
        this.lst = lst;
        this.mItemListener = itemClickListsner;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //   final Lists temp = lst.get(position);

        holder.id.setText(lst.get(position).getId());
        holder.name.setText(lst.get(position).getName());
        holder.prc.setText(lst.get(position).getPrc());
        holder.add.setText(lst.get(position).getAddress());
        holder.description.setText(lst.get(position).getDes());
        holder.park.setText(lst.get(position).getGarage());
        holder.net.setText(lst.get(position).getNet());
        holder.email.setText(lst.get(position).getMail());
        holder.number.setText(lst.get(position).getPnumber());
        holder.post.setText(lst.get(position).getTle());
        holder.date.setText(lst.get(position).getCrt());

        Picasso.with(inflater.getContext())
                .load(lst.get(position).getMimage())
                .placeholder(R.drawable.bed)
                .fit()
                .into(holder.ImgView);

        holder.itemView.setOnClickListener(view -> {
            mItemListener.onItemClick(lst.get(position));
        });

    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public interface ItemClickListsner{
        void onItemClick(Lists lst);
    }

    public void filterList(ArrayList<Lists> filteredList) {
        lst = filteredList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id, name, add, prc, description, park, net, post,email,number,date;
        ImageView ImgView;
        //  CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewRecy1);
            name = itemView.findViewById(R.id.textViewRecy2);
            prc = itemView.findViewById(R.id.textViewRecy3);
            add = itemView.findViewById((R.id.textViewRecy4));
            description = itemView.findViewById(R.id.textViewRecy5);
            park = itemView.findViewById(R.id.textViewRecy6);
            net = itemView.findViewById(R.id.textViewRecy7);
            post = itemView.findViewById(R.id.textViewRecy8);
            email = itemView.findViewById(R.id.textViewRecy9);
            number = itemView.findViewById(R.id.textViewRecy10);
            date = itemView.findViewById(R.id.textViewRecy11);
            ImgView = itemView.findViewById(R.id.ImageRecy);

        }
    }
}