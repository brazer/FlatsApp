package by.onliner.flatsapp.presenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import by.onliner.flatsapp.contract.MainContract;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.network.API;
import by.onliner.flatsapp.network.Response.ApartmentsResponse;
import by.onliner.flatsapp.utils.pagination.PaginationTool;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by Admin on 05.11.2016.
 */

public class MainPresenter implements MainContract.UserActionsListener {

    private MainContract.View mView;
    private Subscription mPagingSubscription;

    public MainPresenter(@NonNull MainContract.View view) {
        mView = view;
    }

    @Override
    public void open(Apartment apartment) {
        mView.show(apartment);
    }

    @Override
    public void loadApartments(RecyclerView recyclerView) {
        PaginationTool<ApartmentsResponse> paginationTool =
                PaginationTool.buildPagingObservable(recyclerView, this::getResponse).build();
        mPagingSubscription = paginationTool
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
                        mView.show(response.getApartments());
                    }
                });
    }

    private Observable<ApartmentsResponse> getResponse(int page) {
        return API.getInstance().getService().getApartments(page);
    }

    @Override
    public void onDestroy() {
        if (mPagingSubscription != null && !mPagingSubscription.isUnsubscribed()) {
            mPagingSubscription.unsubscribe();
        }
    }

}
