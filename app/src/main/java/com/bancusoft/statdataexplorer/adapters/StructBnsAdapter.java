package com.bancusoft.statdataexplorer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.models.StructBns;

import org.jspecify.annotations.NonNull;

import java.util.List;

public class StructBnsAdapter extends RecyclerView.Adapter<StructBnsAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(StructBns struct);
    }

    private List<StructBns> list;
    private OnItemClickListener listener;

    public StructBnsAdapter(List<StructBns> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StructBns item = list.get(position);
        ((TextView)holder.itemView).setText(item.getName());
        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) { super(itemView); }
    }
}
