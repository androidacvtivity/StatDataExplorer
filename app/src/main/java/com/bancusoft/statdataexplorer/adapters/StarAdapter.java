package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.StarModel;
import java.util.List;

public class StarAdapter extends RecyclerView.Adapter<StarAdapter.ViewHolder> {
    private final Context context;
    private final List<StarModel> stars;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onClick(StarModel star);
    }

    public StarAdapter(Context context, List<StarModel> stars, OnItemClickListener listener) {
        this.context = context;
        this.stars = stars;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_star, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StarModel star = stars.get(position);
        holder.txtStar.setText(star.getStar());
        holder.itemView.setOnClickListener(v -> listener.onClick(star));
    }

    @Override
    public int getItemCount() { return stars.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtStar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStar = itemView.findViewById(R.id.txtStar);
        }
    }
}
