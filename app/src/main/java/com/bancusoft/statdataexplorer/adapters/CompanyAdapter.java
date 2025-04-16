package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
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

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder> {

    private final Context context;
    private final List<CompanyModel> companyList;
    private String searchText = "";


    private List<CompanyModel> originalList;
    private List<CompanyModel> filteredList;

    public CompanyAdapter(Context context, List<CompanyModel> companyList) {
        this.context = context;
        this.companyList = companyList;
        this.originalList = new ArrayList<>(companyList);
        this.filteredList = new ArrayList<>(companyList);
    }


    public void setSearchText(String searchText) {
        this.searchText = searchText != null ? searchText.toLowerCase(Locale.getDefault()).trim() : "";
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.ViewHolder holder, int position) {
        CompanyModel company = filteredList.get(position);

//        highlightText(holder.tvDenumire, company.getDenumire(), searchText);
        highlightTextExact(holder.tvDenumire, company.getDenumire(), searchText);
//        highlightText(holder.tvIdno, company.getIdno(), searchText);
//        highlightText(holder.tvConditii, company.getConditii(), searchText);
//        highlightText(holder.tvFondatori, company.getFondatori(), searchText);
        highlightTextExact(holder.tvIdno, company.getIdno(), searchText);
        highlightTextExact(holder.tvConditii, company.getConditii(), searchText);
        highlightTextExact(holder.tvFondatori, company.getFondatori(), searchText);

        holder.tvAdresa.setText(company.getAdresa());
        holder.tvForma.setText(company.getFormaOrganizare());
        holder.tvFaraLicenta.setText(company.getActivitatiFaraLicenta());
        holder.tvCuLicenta.setText(company.getActivitatiCuLicenta());
        holder.tvStatut.setText(company.getStatutul());
        holder.tvData.setText(company.getDataInregistrarii());
        holder.tvAct.setText(company.getAct());

        // 🔗 Click pe item → deschide CompanyDetailsActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, CompanyDetailsActivity.class);
            intent.putExtra("company", company); // Trebuie să fie Serializable
            context.startActivity(intent);
        });
    }

    public void filter(String query) {
        searchText = query; // pentru highlight
        filteredList.clear();

        if (query.isEmpty()) {
            filteredList.addAll(originalList);
        } else {
            for (CompanyModel item : originalList) {
                if (item.getDenumire().toLowerCase().contains(query) ||
                        item.getIdno().toLowerCase().contains(query) ||
                        item.getConditii().toLowerCase().contains(query) ||
                        item.getFondatori().toLowerCase().contains(query)) {
                    filteredList.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDenumire, tvIdno, tvConditii, tvFondatori;
        TextView tvAdresa, tvForma, tvFaraLicenta, tvCuLicenta;
        TextView tvStatut, tvData, tvAct;

        public ViewHolder(@NonNull View itemView) {
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

    private void highlightText(TextView textView, String originalText, String searchText) {
        if (originalText == null || searchText == null || searchText.isEmpty()) {
            textView.setText(originalText);
            return;
        }

        String lowerOriginal = originalText.toLowerCase(Locale.getDefault());
        int startPos = lowerOriginal.indexOf(searchText);

        if (startPos == -1) {
            textView.setText(originalText);
            return;
        }

        int endPos = startPos + searchText.length();
        Spannable spanString = Spannable.Factory.getInstance().newSpannable(originalText);
        spanString.setSpan(new BackgroundColorSpan(Color.YELLOW), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanString.setSpan(new ForegroundColorSpan(Color.BLACK), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanString);
    }

    private void highlightTextExact(TextView textView, String fullText, String searchText) {
        if (searchText == null || searchText.isEmpty()) {
            textView.setText(fullText);
            return;
        }

        String lowerFullText = fullText.toLowerCase(Locale.getDefault());
        String lowerSearchText = searchText.toLowerCase(Locale.getDefault());

        int startPos = lowerFullText.indexOf(lowerSearchText);
        if (startPos == -1) {
            textView.setText(fullText);
            return;
        }

        int endPos = startPos + lowerSearchText.length();

        Spannable spannable = Spannable.Factory.getInstance().newSpannable(fullText);
        spannable.setSpan(new BackgroundColorSpan(Color.YELLOW), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new ForegroundColorSpan(Color.BLACK), startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(spannable);
    }

}
