package by.onliner.flatsapp.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.network.API;
import by.onliner.flatsapp.network.Response.ApartmentsResponse;
import by.onliner.flatsapp.ui.adapter.FlatsAdapter;
import by.onliner.flatsapp.utils.pagination.PaginationTool;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class FlatsFragment extends Fragment {

    public final static String TAG = FlatsFragment.class.getSimpleName();
    private int mLimit = 30;
    private FlatsAdapter mAdapter;
    private RecyclerView recyclerView;
    private Subscription pagingSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_flats, container, false);
        setRetainInstance(true);
        init(rootView);
        return rootView;
    }

    private void init(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();
        // init adapter for the first time
        mAdapter = new FlatsAdapter();
        mAdapter.setHasStableIds(true);
        recyclerView.setSaveEnabled(true);

        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(mAdapter);
        if (mAdapter.areAllApartmentsLoaded()) return;

        // RecyclerView pagination
        PaginationTool<ApartmentsResponse> paginationTool =
                PaginationTool.buildPagingObservable(
                        recyclerView,
                        offset -> getResponse(offset, mLimit))
                        .setLimit(mLimit)
                        .build();


        pagingSubscription = paginationTool
                .getPagingObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ApartmentsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(ApartmentsResponse response) {
                        mAdapter.addNewApartments(response.getApartments());
                        mAdapter.notifyItemInserted(mAdapter.getItemCount() - response.getApartments().size());
                    }
                });
    }

    private int errorCount = 0;
    public Observable<ApartmentsResponse> getResponse(int page, int limit) {
        if (page == 200 && errorCount < 1) {
            errorCount++;
            return Observable
                    .error(new RuntimeException("error"));
        } else {
            return API.getInstance().getService().getApartments(page);
        }
    }

    @Override
    public void onDestroyView() {
        if (pagingSubscription != null && !pagingSubscription.isUnsubscribed()) {
            pagingSubscription.unsubscribe();
        }
        // for memory leak prevention (RecycleView is not unsubscibed from adapter DataObserver)
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
        }
        super.onDestroyView();
    }

}
