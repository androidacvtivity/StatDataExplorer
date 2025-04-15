package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.Scientistsvw;

import java.util.List;

public class ScientistAdapter extends RecyclerView.Adapter<ScientistAdapter.ViewHolder> {

    private final Context context;
    private final List<Scientistsvw> list;

    public ScientistAdapter(Context context, List<Scientistsvw> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_scientist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Scientistsvw s = list.get(position);
        holder.txtDenumire.setText(s.getDEN_COM_VW());
        holder.txtIdno.setText(s.getIDNO_VW());
        holder.txtAdresa.setText(s.getADRESA_VW());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDenumire, txtIdno, txtAdresa;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDenumire = itemView.findViewById(R.id.txtDenumire);
            txtIdno = itemView.findViewById(R.id.txtIdno);
            txtAdresa = itemView.findViewById(R.id.txtAdresa);
        }
    }
}
