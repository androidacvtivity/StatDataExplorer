package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.activities.EmployeesByGroupActivity;
import com.bancusoft.statdataexplorer.models.SimpleValueModel;

import java.util.List;

public class SimpleValueAdapter extends RecyclerView.Adapter<SimpleValueAdapter.ViewHolder> {

    Context context;
    List<SimpleValueModel> list;
    int tip; // 0=star, 1=depart, 2=sectia, 3=serviciu

    public SimpleValueAdapter(Context context, List<SimpleValueModel> list, int tip) {
        this.context = context;
        this.list = list;
        this.tip = tip;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_simple_value, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SimpleValueModel model = list.get(position);
        String valoare = model.getValue();
        holder.textView.setText(valoare);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, EmployeesByGroupActivity.class);
            intent.putExtra("tip", getTipAsString(tip));
            intent.putExtra("valoare", valoare);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvSimpleValue);
        }
    }

    private String getTipAsString(int tip) {
        switch (tip) {
            case 0: return "star";
            case 1: return "depart";
            case 2: return "sectia";
            case 3: return "serviciu";
            default: return "unknown";
        }
    }
}
