package by.onliner.flatsapp.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.presenter.MainPresenter;
import by.onliner.flatsapp.utils.CurrencyBuilder;
import by.onliner.flatsapp.utils.DateUtil;
import by.onliner.flatsapp.utils.RoomParser;

public class DetailActivity extends AppCompatActivity {

    public final static String ARG_APARTMENT = "apartment";
    private Apartment mApartment;

    private ImageView mApartmentIv;
    private TextView mUpTv;
    private TextView mNewRoublesTv;
    private TextView mDollarsAndOldRoublesTv;
    private TextView mRoomTv;
    private TextView mOwnerTv;
    private TextView mAddressTv;
    private TextView mDatePublishTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setApartment();
        initViews();
        bindViews();
    }

    private void setApartment() {
        mApartment = getIntent().getParcelableExtra(ARG_APARTMENT);
        if (mApartment == null)
            throw new IllegalArgumentException("Empty args");
    }

    private void initViews() {
        mApartmentIv = (ImageView) findViewById(R.id.detail_cover);
        mUpTv = (TextView) findViewById(R.id.detail_up);
        mNewRoublesTv = (TextView) findViewById(R.id.detail_new_roubles);
        mDollarsAndOldRoublesTv = (TextView) findViewById(R.id.detail_dollars_and_old_roubles);
        mRoomTv = (TextView) findViewById(R.id.detail_room);
        mOwnerTv = (TextView) findViewById(R.id.detail_owner);
        mAddressTv = (TextView) findViewById(R.id.detail_address);
        mDatePublishTv = (TextView) findViewById(R.id.detail_date_added);
    }

    private void bindViews() {
        Picasso.with(this).load(mApartment.getPhoto()).into(mApartmentIv);
        DateTime date = new DateTime(mApartment.getLastTimeUp().getTime());
        String term = getResources().getString(R.string.up) + " " +
                DateUtil.getTerm(date, getResources());
        mUpTv.setText(term);
        String newRoublesStr = CurrencyBuilder.getNewRoubles(
                mApartment.getPrice().getConverted().getBYN().getAmount(),
                getResources()
        );
        mNewRoublesTv.setText(newRoublesStr);
        String dollarsAndOldRoubles = CurrencyBuilder.getDollarsAndOldRoubles(
                mApartment.getPrice().getConverted().getUSD().getAmount(),
                mApartment.getPrice().getConverted().getBYR().getAmount(),
                getResources()
        );
        mDollarsAndOldRoublesTv.setText(dollarsAndOldRoubles);
        mRoomTv.setText(RoomParser.getRooms(mApartment.getRentType(), getResources()));
        if (mApartment.getContact().isOwner()) {
            mOwnerTv.setText(R.string.owner);
        } else mOwnerTv.setText(R.string.no_owner);
        mAddressTv.setText(mApartment.getLocation().getAddress());
        DateTime publishingDate = new DateTime(mApartment.getCreatedAt().getTime());
        String published = getResources().getString(R.string.published) + " " +
                DateUtil.getTerm(publishingDate, getResources()).toLowerCase();
        mDatePublishTv.setText(published);
    }

}
