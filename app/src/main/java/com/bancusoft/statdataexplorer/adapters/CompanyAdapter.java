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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private final Context context;
    private final List<CompanyModel> originalList;
    private final List<CompanyModel> filteredList;

    private String searchQuery = "";

    public CompanyAdapter(Context context, List<CompanyModel> companyList) {
        this.context = context;
        this.originalList = new ArrayList<>(companyList);
        this.filteredList = new ArrayList<>(companyList);
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        return new CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyViewHolder holder, int position) {
        CompanyModel company = filteredList.get(position);

        holder.tvDenumire.setText(getHighlightedText(company.getDenumire(), searchQuery, Color.YELLOW));
        holder.tvIdno.setText(getHighlightedText(company.getIdno(), searchQuery, Color.CYAN));
        holder.tvConditii.setText(getHighlightedText(company.getConditii(), searchQuery, Color.GREEN));
        holder.tvFondatori.setText(getHighlightedText(company.getFondatori(), searchQuery, Color.MAGENTA));
        holder.tvAdresa.setText(company.getAdresa());
        holder.tvForma.setText(company.getFormaOrganizare());
        holder.tvFaraLicenta.setText(company.getActivitatiFaraLicenta());
        holder.tvCuLicenta.setText(company.getActivitatiCuLicenta());
        holder.tvStatut.setText(company.getStatutul());
        holder.tvData.setText(company.getDataInregistrarii());
        holder.tvAct.setText(company.getAct());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CompanyDetailsActivity.class);
            intent.putExtra("company", company);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public void filter(String query) {
        searchQuery = query.toLowerCase(Locale.getDefault());
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (CompanyModel item : originalList) {
                if ((item.getDenumire() != null && item.getDenumire().toLowerCase().contains(searchQuery)) ||
                        (item.getIdno() != null && item.getIdno().toLowerCase().contains(searchQuery)) ||
                        (item.getConditii() != null && item.getConditii().toLowerCase().contains(searchQuery)) ||
                        (item.getFondatori() != null && item.getFondatori().toLowerCase().contains(searchQuery))) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    private Spannable getHighlightedText(String original, String query, int color) {
        if (original == null) return new SpannableString("");
        Spannable spannable = new SpannableString(original);
        if (query == null || query.isEmpty()) return spannable;

        String lower = original.toLowerCase();
        int start = lower.indexOf(query);
        if (start >= 0) {
            spannable.setSpan(new ForegroundColorSpan(color), start, start + query.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    static class CompanyViewHolder extends RecyclerView.ViewHolder {
        TextView tvDenumire, tvIdno, tvConditii, tvFondatori, tvAdresa, tvForma,
                tvFaraLicenta, tvCuLicenta, tvStatut, tvData, tvAct;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDenumire = itemView.findViewById(R.id.txtDenumire);
            tvIdno = itemView.findViewById(R.id.txtIdno);
            tvConditii = itemView.findViewById(R.id.txtConditii);
            tvFondatori = itemView.findViewById(R.id.txtFondatori);
            tvAdresa = itemView.findViewById(R.id.txtAdresa);
            tvForma = itemView.findViewById(R.id.txtForma);
            tvFaraLicenta = itemView.findViewById(R.id.txtFaraLicenta);
            tvCuLicenta = itemView.findViewById(R.id.txtCuLicenta);
            tvStatut = itemView.findViewById(R.id.txtStatut);
            tvData = itemView.findViewById(R.id.txtData);
            tvAct = itemView.findViewById(R.id.txtAct);
        }
    }
}
