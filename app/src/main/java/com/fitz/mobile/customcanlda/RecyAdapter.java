package com.fitz.mobile.customcanlda;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ItemViewHolder> {

    List<RecyModel> models;
    Context mContext;

    public RecyAdapter(Context context,List<RecyModel> models) {
        this.models = models;
        mContext = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recy_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.title.setText( models.get(position).title);

        holder.time.setText( models.get(position).time);
        holder.location1.setText( models.get(position).location1);
        holder.location2.setText( models.get(position).location2);
        holder.now.setVisibility(models.get(position).isNow?View.VISIBLE:View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return models == null?0:models.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{



        TextView title,time,location1,location2,now;
        public ItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            location1 = itemView.findViewById(R.id.location1);
            location2 = itemView.findViewById(R.id.location2);
            now = itemView.findViewById(R.id.now);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"被点击："+getAdapterPosition(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
