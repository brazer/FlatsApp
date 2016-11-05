package by.onliner.flatsapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.contract.MainContract;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.presenter.MainPresenter;
import by.onliner.flatsapp.ui.activity.DetailActivity;
import by.onliner.flatsapp.ui.adapter.ApartmentAdapter;

public class FlatsFragment extends Fragment
        implements MainContract.View, ApartmentAdapter.OnApartmentClickListener {

    public final static String TAG = FlatsFragment.class.getSimpleName();
    private ApartmentAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private MainContract.UserActionsListener mActionListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionListener = new MainPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flats, container, false);
        setRetainInstance(true);
        init(rootView);
        return rootView;
    }

    private void init(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        GridLayoutManager recyclerViewLayoutManager =
                new GridLayoutManager(getActivity(), getResources().getInteger(R.integer.column_num));
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();
        // init adapter for the first time
        mAdapter = new ApartmentAdapter(this);
        mAdapter.setHasStableIds(true);
        mRecyclerView.setSaveEnabled(true);

        mRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        if (mAdapter.areAllApartmentsLoaded()) return;

        mActionListener.loadApartments(mRecyclerView);
    }

    @Override
    public void show(List<Apartment> apartments) {
        mAdapter.addNewApartments(apartments);
        mAdapter.notifyItemInserted(mAdapter.getItemCount() - apartments.size());
    }

    @Override
    public void show(Apartment apartment) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_APARTMENT, apartment);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        mActionListener.onDestroy();
        // for memory leak prevention (RecycleView is not unsubscibed from adapter DataObserver)
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(null);
        }
        super.onDestroyView();
    }

    @Override
    public void onApartmentClick(int position) {
        mActionListener.open(mAdapter.getItem(position));
    }
}
