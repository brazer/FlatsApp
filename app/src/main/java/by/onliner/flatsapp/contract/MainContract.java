package by.onliner.flatsapp.contract;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import by.onliner.flatsapp.model.Apartment;

/**
 * Created by Admin on 05.11.2016.
 */

public interface MainContract {

    interface View {
        void show(List<Apartment> apartments);
        void show(Apartment apartment);
    }

    interface UserActionsListener {
        void open(Apartment apartment);
        void loadApartments(RecyclerView recyclerView);
        void onDestroy();
    }

}
