package by.onliner.flatsapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.utils.CurrencyBuilder;
import by.onliner.flatsapp.utils.DateUtil;
import by.onliner.flatsapp.utils.RoomParser;

public class ApartmentAdapter extends RecyclerView.Adapter<ApartmentAdapter.ViewHolder> {

    public interface OnApartmentClickListener {
        void onApartmentClick(int position);
    }

    private List<Apartment> mApartments = new ArrayList<>();
    private boolean allApartmentsLoaded;
    private OnApartmentClickListener mListener;

    public ApartmentAdapter(OnApartmentClickListener listener) {
        mListener = listener;
    }

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
    public ApartmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.flat_item, parent, false);
        return new ApartmentAdapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ApartmentAdapter.ViewHolder holder, int position) {
        Apartment apartment = getItem(position);
        onBindHolder(holder, apartment);
    }

    private void onBindHolder(ViewHolder holder, Apartment apartment) {
        DateTime date = new DateTime(apartment.getLastTimeUp().getTime());
        String term = holder.itemView.getResources().getString(R.string.up) + " " +
                DateUtil.getTerm(date, holder.itemView.getResources());
        holder.mUpTv.setText(term);
        String dollarsAndOldRoubles = CurrencyBuilder.getDollarsAndOldRoubles(
                apartment.getPrice().getConverted().getUSD().getAmount(),
                apartment.getPrice().getConverted().getBYR().getAmount(),
                holder.itemView.getResources()
        );
        holder.mPriceInDollarsAndOldRoublesTv.setText(dollarsAndOldRoubles);
        String newRoublesStr = CurrencyBuilder.getNewRoubles(
                apartment.getPrice().getConverted().getBYN().getAmount(),
                holder.itemView.getResources()
        );
        holder.mPriceInNewRoublesTv.setText(newRoublesStr);
        holder.mParamsTv.setText(RoomParser.getRooms(apartment.getRentType(), holder.mParamsTv.getResources()));
        holder.mAddressTv.setText(apartment.getLocation().getAddress());
        Picasso.with(holder.mCoverIv.getContext())
                .load(apartment.getPhoto()).placeholder(R.drawable.home).into(holder.mCoverIv);
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mUpTv;
        TextView mPriceInDollarsAndOldRoublesTv;
        TextView mPriceInNewRoublesTv;
        TextView mParamsTv;
        TextView mAddressTv;
        ImageView mCoverIv;

        private OnApartmentClickListener mListener;

        ViewHolder(View itemView, OnApartmentClickListener listener) {
            super(itemView);
            mUpTv = (TextView) itemView.findViewById(R.id.up);
            mPriceInDollarsAndOldRoublesTv = (TextView) itemView.findViewById(R.id.text_dollar_and_old_roubles);
            mPriceInNewRoublesTv = (TextView) itemView.findViewById(R.id.text_new_roubles);
            mParamsTv = (TextView) itemView.findViewById(R.id.text_params);
            mAddressTv = (TextView) itemView.findViewById(R.id.text_address);
            mCoverIv = (ImageView) itemView.findViewById(R.id.image_flat);
            itemView.setOnClickListener(this);
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mListener.onApartmentClick(position);
        }
    }

}
