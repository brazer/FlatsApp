package by.onliner.flatsapp.network;

import by.onliner.flatsapp.network.Response.ApartmentsResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface FlatsServiceApi {

    @GET(ApiConstants.APARTMENTS)
    void getApartments(@Query("page") int page, Callback<ApartmentsResponse> callback);

}
