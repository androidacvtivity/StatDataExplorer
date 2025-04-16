// CompanyAdapter.java
package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.models.CompanyModel;
import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private final Context context;
    private final List<CompanyModel> companyList;

    public CompanyAdapter(Context context, List<CompanyModel> companyList) {
        this.context = context;
        this.companyList = companyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CompanyModel c = companyList.get(position);
        holder.txtDenumire.setText(c.getDenumire());
        holder.txtIdno.setText(c.getIdno());
        holder.txtAdresa.setText(c.getAdresa());
        holder.txtForma.setText(c.getFormaOrganizare());
        holder.txtConditii.setText(c.getConditii());
        holder.txtFondatori.setText(c.getFondatori());
        holder.txtFaraLicenta.setText(c.getActivitatiFaraLicenta());
        holder.txtCuLicenta.setText(c.getActivitatiCuLicenta());
        holder.txtStatut.setText(c.getStatutul());
        holder.txtData.setText(c.getDataInregistrarii());
        holder.txtAct.setText(c.getAct());
    }

    @Override
    public int getItemCount() {
        return (companyList != null) ? companyList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDenumire, txtIdno, txtAdresa, txtForma, txtConditii, txtFondatori,
                txtFaraLicenta, txtCuLicenta, txtStatut, txtData, txtAct;

        public ViewHolder(View itemView) {
            super(itemView);
            txtDenumire = itemView.findViewById(R.id.txtDenumire);
            txtIdno = itemView.findViewById(R.id.txtIdno);
            txtAdresa = itemView.findViewById(R.id.txtAdresa);
            txtForma = itemView.findViewById(R.id.txtForma);
            txtConditii = itemView.findViewById(R.id.txtConditii);
            txtFondatori = itemView.findViewById(R.id.txtFondatori);
            txtFaraLicenta = itemView.findViewById(R.id.txtFaraLicenta);
            txtCuLicenta = itemView.findViewById(R.id.txtCuLicenta);
            txtStatut = itemView.findViewById(R.id.txtStatut);
            txtData = itemView.findViewById(R.id.txtData);
            txtAct = itemView.findViewById(R.id.txtAct);
        }
    }
}
