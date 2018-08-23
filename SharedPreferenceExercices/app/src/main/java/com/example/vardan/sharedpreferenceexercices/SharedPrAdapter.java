package com.example.vardan.sharedpreferenceexercices;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SharedPrAdapter extends RecyclerView.Adapter<SharedPrAdapter.ViewHolder> {

    private Context context;
    private List<Model> modelList;

    SharedPrAdapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public SharedPrAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SharedPrAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.key.setText(modelList.get(position).getKey());
        holder.value.setText(modelList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView key;
        private TextView value;

        ViewHolder(View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.key_id);
            value = itemView.findViewById(R.id.value_id);
        }
    }
}
