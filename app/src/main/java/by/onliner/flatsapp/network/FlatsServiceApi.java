package by.onliner.flatsapp.network;

import by.onliner.flatsapp.network.Response.ApartmentsResponse;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface FlatsServiceApi {

    @GET(ApiConstants.APARTMENTS)
    Observable<ApartmentsResponse> getApartments(@Query("page") int page);

}
