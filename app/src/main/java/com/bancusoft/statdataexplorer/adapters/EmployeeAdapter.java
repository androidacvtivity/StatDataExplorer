package com.bancusoft.statdataexplorer.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bancusoft.statdataexplorer.R;
import com.bancusoft.statdataexplorer.activities.EmployeeDetailsActivity;
import com.bancusoft.statdataexplorer.models.EmployeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private final Context context;
    private List<EmployeeModel> employees;

    public EmployeeAdapter(Context context, List<EmployeeModel> employees) {
        this.context = context;
        this.employees = new ArrayList<>(employees);
        this.originalList.addAll(employees); // păstrăm copia originală
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmployeeModel e = employees.get(position);

        holder.txtName.setText(getHighlightedText(e.getName(), searchQuery, Color.GREEN));
        holder.txtDescription.setText(e.getDescription());
        holder.txtGalaxy.setText(e.getGalaxy());
        holder.txtStar.setText(e.getStar());
        holder.txtServiciu.setText(e.getServiciu());
        holder.txtSectia.setText(e.getSectia());
        holder.txtDepart.setText(e.getDepart());
        holder.txtPhone.setText(e.getPhone());
        holder.txtPhoneInternal.setText(e.getPhoneinternal());
        holder.txtEmail.setText(e.getEmail());
        holder.txtEmail.setTextColor(ContextCompat.getColor(context, R.color.link_blue));
        holder.txtEmail.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + e.getEmail()));
            context.startActivity(emailIntent);
        });
        holder.txtPersonalInfo.setText(e.getPersonalinfo());
        holder.txtFormName.setText(e.getFormname());
        holder.txtPhoneMobil.setText(getHighlightedText(e.getPhonemobil(), searchQuery, Color.CYAN));
        holder.txtFloor.setText(e.getFloor());
        holder.txtOffice.setText(e.getOffice());
        holder.txtNotice.setText(e.getNotice());

        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, EmployeeDetailsActivity.class);
            i.putExtra("employee", e);
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    private String searchQuery = "";
    private final List<EmployeeModel> originalList = new ArrayList<>();

    public void filter(String query) {
        searchQuery = query.toLowerCase(Locale.getDefault());
        employees.clear();
        if (query.isEmpty()) {
            employees.addAll(originalList);
        } else {
            for (EmployeeModel item : originalList) {
                if ((item.getName() != null && item.getName().toLowerCase().contains(searchQuery)) ||
//                        (item.getEmail() != null && item.getEmail().toLowerCase().contains(searchQuery)) ||
                        (item.getPhonemobil() != null && item.getPhonemobil().toLowerCase().contains(searchQuery))
//                        (item.getServiciu() != null && item.getServiciu().toLowerCase().contains(searchQuery))
                ) {
                    employees.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void updateData(List<EmployeeModel> newEmployees) {
        this.employees = new ArrayList<>(newEmployees);
        this.originalList.clear();
        this.originalList.addAll(newEmployees);
        filter(searchQuery);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtDescription, txtGalaxy, txtStar, txtServiciu, txtSectia, txtDepart,
                txtPhone, txtPhoneInternal, txtEmail, txtPersonalInfo, txtFormName,
                txtPhoneMobil, txtFloor, txtOffice, txtNotice;

        public ViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.txtName);
            txtDescription = v.findViewById(R.id.txtDescription);
            txtGalaxy = v.findViewById(R.id.txtGalaxy);
            txtStar = v.findViewById(R.id.txtStar);
            txtServiciu = v.findViewById(R.id.txtServiciu);
            txtSectia = v.findViewById(R.id.txtSectia);
            txtDepart = v.findViewById(R.id.txtDepart);
            txtPhone = v.findViewById(R.id.txtPhone);
            txtPhoneInternal = v.findViewById(R.id.txtPhoneInternal);
            txtEmail = v.findViewById(R.id.txtEmail);
            txtPersonalInfo = v.findViewById(R.id.txtPersonalInfo);
            txtFormName = v.findViewById(R.id.txtFormName);
            txtPhoneMobil = v.findViewById(R.id.txtPhoneMobil);
            txtFloor = v.findViewById(R.id.txtFloor);
            txtOffice = v.findViewById(R.id.txtOffice);
            txtNotice = v.findViewById(R.id.txtNotice);
        }
    }
}
