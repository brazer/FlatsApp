package by.onliner.flatsapp.network.Response;

import java.util.ArrayList;

import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.model.Page;

/**
 * Created by Admin on 03.11.2016.
 */

public class ApartmentsResponse {

    private ArrayList<Apartment> apartments;

    private int total;

    private Page page;

    public ArrayList<Apartment> getApartments() {
        return apartments;
    }

    public int getTotal() {
        return total;
    }

    public Page getPage() {
        return page;
    }
}
