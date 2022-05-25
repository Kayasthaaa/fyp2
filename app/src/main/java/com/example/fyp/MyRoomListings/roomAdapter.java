package com.example.fyp.MyRoomListings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fyp.R;
import com.example.fyp.RecyclerView.Adapter;
import com.example.fyp.RecyclerView.Lists;

import java.util.List;

public class roomAdapter extends RecyclerView.Adapter<roomAdapter.ViewHolder> {

    LayoutInflater layoutInflater;
    List<roomLists> list;
    private OnClickListener onClickListener;


    public roomAdapter(Context ctx, List<roomLists> list, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(ctx);
        this.list = list;
        this.onClickListener = onClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.mylistingscustom, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.roomId.setText(list.get(position).getId());
        holder.Addedname.setText(list.get(position).getPoster());
        holder.price.setText(list.get(position).getPrice());
        holder.address.setText(list.get(position).getLocation());
        holder.Description.setText(list.get(position).getDescription());
        holder.parking.setText(list.get(position).getParking());
        holder.Inet.setText(list.get(position).getParking());
        holder.Email.setText(list.get(position).getEmail());
        holder.Pnumber.setText(list.get(position).getPhone_number());
        holder.poster.setText(list.get(position).getTitle());
        holder.Date.setText(list.get(position).getCreated());
        //  Picasso.get().load(lst.get(position).getMimage()).into(holder.ImgView);

        holder.itemView.setOnClickListener(view -> {
            onClickListener.onClick(list.get(position));
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnClickListener{
        void onClick(roomLists list);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView roomId, Addedname, address, price, Description, parking, Inet, poster,Email,Pnumber,Date;
        ImageView Image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            roomId = itemView.findViewById(R.id.textView1);
            Addedname = itemView.findViewById(R.id.textView2);
            address = itemView.findViewById(R.id.textView3);
            price = itemView.findViewById((R.id.textView4));
            Description = itemView.findViewById(R.id.textView5);
            parking = itemView.findViewById(R.id.textView6);
            Inet = itemView.findViewById(R.id.textView7);
            poster = itemView.findViewById(R.id.textView8);
            Email = itemView.findViewById(R.id.textView9);
            Pnumber = itemView.findViewById(R.id.textView10);
            Date = itemView.findViewById(R.id.textView11);
            Image = itemView.findViewById(R.id.ImageRecycler);
        }
    }
}
