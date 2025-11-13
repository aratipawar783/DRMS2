// MarketAdapter.java
package com.example.smartcropapp.marketprice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcropapp.R;

import java.util.List;

public class MarketAdapter extends RecyclerView.Adapter<MarketAdapter.MarketViewHolder> {

    private List<MarketResponse.Report> reports;

    public MarketAdapter(List<MarketResponse.Report> reports) {
        this.reports = reports;
    }

    public void updateData(List<MarketResponse.Report> newReports) {
        reports.clear();
        reports.addAll(newReports);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MarketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report, parent, false);
        return new MarketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketViewHolder holder, int position) {
        MarketResponse.Report report = reports.get(position);
        holder.title.setText(report.title);
        holder.market.setText(report.market);
        holder.price.setText(String.valueOf(report.price));
        holder.date.setText(report.date);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    static class MarketViewHolder extends RecyclerView.ViewHolder {
        TextView title, market, price, date;

        public MarketViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            market = itemView.findViewById(R.id.market);
            price = itemView.findViewById(R.id.price);
            date = itemView.findViewById(R.id.date);
        }
    }
}
