package by.onliner.flatsapp.contract;

import by.onliner.flatsapp.model.Apartment;

/**
 * Created by Admin on 05.11.2016.
 */

public interface DetailContract {

    interface View {
        void show(Apartment apartment);
    }

}
