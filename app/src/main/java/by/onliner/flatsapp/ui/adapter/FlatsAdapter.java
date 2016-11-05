package by.onliner.flatsapp.ui.adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Locale;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.utils.RoomParser;

public class FlatsAdapter extends RecyclerView.Adapter<FlatsAdapter.ViewHolder> {

    private List<Apartment> mApartments = new ArrayList<>();

    private boolean allApartmentsLoaded;

    public void addNewApartments(List<Apartment> apartments) {
        if (apartments.size() == 0) {
            allApartmentsLoaded = true;
            return;
        }
        mApartments.addAll(apartments);
    }

    public boolean areAllApartmentsLoaded() {
        return allApartmentsLoaded;
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    public Apartment getItem(int position) {
        return mApartments.get(position);
    }

    @Override
    public int getItemCount() {
        return mApartments.size();
    }

    @Override
    public FlatsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flat_item, parent, false);
        return new FlatsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FlatsAdapter.ViewHolder holder, int position) {
        Apartment apartment = getItem(position);
        onBindHolder(holder, apartment);
    }

    private void onBindHolder(ViewHolder holder, Apartment apartment) {
        double oldRoubles = Double.parseDouble(apartment.getPrice().getConverted().getBYR().getAmount());
        double dollars = Double.parseDouble(apartment.getPrice().getConverted().getUSD().getAmount());
        DecimalFormat oldRoublesFormatter = new DecimalFormat("00,000.00");
        oldRoublesFormatter.setMinimumFractionDigits(0);
        DecimalFormat dollarFormatter = new DecimalFormat("000.00");
        dollarFormatter.setMinimumFractionDigits(0);
        dollarFormatter.setMinimumIntegerDigits(2);
        String dollarsAndOldRoubles = String.format(
                holder.itemView.getContext().getResources().getString(R.string.dollars_and_old_roubles),
                dollarFormatter.format(dollars),
                oldRoublesFormatter.format(oldRoubles)
        );
        holder.mPriceInDollarsAndOldRoublesTv.setText(dollarsAndOldRoubles);
        double newRoubles = Double.parseDouble(apartment.getPrice().getConverted().getBYN().getAmount());
        DecimalFormat newRoublesFormatter = new DecimalFormat("000.00");
        newRoublesFormatter.setMinimumFractionDigits(0);
        newRoublesFormatter.setMinimumIntegerDigits(2);
        String newRoublesStr = String.format(
                holder.itemView.getContext().getString(R.string.new_roubles),
                newRoublesFormatter.format(newRoubles)
        );
        holder.mPriceInNewRoublesTv.setText(newRoublesStr);
        holder.mParamsTv.setText(RoomParser.getRooms(apartment.getRentType(), holder.mParamsTv.getResources()));
        holder.mAddressTv.setText(apartment.getLocation().getAddress());
        Picasso.with(holder.mCoverIv.getContext())
                .load(apartment.getPhoto()).placeholder(R.drawable.home).into(holder.mCoverIv);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mPriceInDollarsAndOldRoublesTv;
        TextView mPriceInNewRoublesTv;
        TextView mParamsTv;
        TextView mAddressTv;
        ImageView mCoverIv;

        ViewHolder(View itemView) {
            super(itemView);
            mPriceInDollarsAndOldRoublesTv = (TextView) itemView.findViewById(R.id.text_dollar_and_old_roubles);
            mPriceInNewRoublesTv = (TextView) itemView.findViewById(R.id.text_new_roubles);
            mParamsTv = (TextView) itemView.findViewById(R.id.text_params);
            mAddressTv = (TextView) itemView.findViewById(R.id.text_address);
            mCoverIv = (ImageView) itemView.findViewById(R.id.image_flat);
        }
    }

}
