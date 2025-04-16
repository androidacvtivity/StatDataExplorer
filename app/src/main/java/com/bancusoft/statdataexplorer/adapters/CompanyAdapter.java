package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.activities.CompanyDetailsActivity;
import com.bancusoft.statdataexplorer.models.CompanyModel;
import java.util.List;
import java.util.Locale;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private final Context context;
    private final List<CompanyModel> companyList;
    private final String searchString;

    public CompanyAdapter(Context context, List<CompanyModel> companyList) {
        this(context, companyList, "");
    }

    public CompanyAdapter(Context context, List<CompanyModel> companyList, String searchString) {
        this.context = context;
        this.companyList = companyList;
        this.searchString = searchString.toLowerCase(Locale.getDefault());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompanyModel company = companyList.get(position);

        highlightText(holder.tvDenumire, company.getDenumire());
        highlightText(holder.tvIdno, company.getIdno());
        highlightText(holder.tvConditii, company.getConditii());
        highlightText(holder.tvFondatori, company.getFondatori());

        holder.txtAdresa.setText(company.getAdresa());
        holder.txtForma.setText(company.getFormaOrganizare());
        holder.txtFaraLicenta.setText(company.getActivitatiFaraLicenta());
        holder.txtCuLicenta.setText(company.getActivitatiCuLicenta());
        holder.txtStatut.setText(company.getStatutul());
        holder.txtData.setText(company.getDataInregistrarii());
        holder.txtAct.setText(company.getAct());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CompanyDetailsActivity.class);
            intent.putExtra("company", company);
            context.startActivity(intent);
        });
    }

    private void highlightText(TextView textView, String text) {
        if (searchString.isEmpty() || text == null) {
            textView.setText(text);
            return;
        }
        String lowerText = text.toLowerCase(Locale.getDefault());
        int start = lowerText.indexOf(searchString);
        if (start < 0) {
            textView.setText(text);
            return;
        }
        int end = start + searchString.length();
        SpannableString spannable = new SpannableString(text);
        spannable.setSpan(new ForegroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannable);
    }

    @Override
    public int getItemCount() {
        return companyList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDenumire, tvIdno, tvConditii, tvFondatori;
        TextView txtAdresa, txtForma, txtFaraLicenta, txtCuLicenta, txtStatut, txtData, txtAct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDenumire = itemView.findViewById(R.id.txtDenumire);
            tvIdno = itemView.findViewById(R.id.txtIdno);
            tvConditii = itemView.findViewById(R.id.txtConditii);
            tvFondatori = itemView.findViewById(R.id.txtFondatori);
            txtAdresa = itemView.findViewById(R.id.txtAdresa);
            txtForma = itemView.findViewById(R.id.txtForma);
            txtFaraLicenta = itemView.findViewById(R.id.txtFaraLicenta);
            txtCuLicenta = itemView.findViewById(R.id.txtCuLicenta);
            txtStatut = itemView.findViewById(R.id.txtStatut);
            txtData = itemView.findViewById(R.id.txtData);
            txtAct = itemView.findViewById(R.id.txtAct);
        }
    }
}
