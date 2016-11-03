package by.onliner.flatsapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.network.API;
import by.onliner.flatsapp.network.Response.ApartmentsResponse;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Apartment> mApartments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        API.getInstance().getService().getApartments(1, new FlatsResponse());
    }

    private class FlatsResponse implements Callback<ApartmentsResponse> {

        @Override
        public void success(ApartmentsResponse apartmentsResponse, Response response) {
            mApartments = apartmentsResponse.getApartments();
        }

        @Override
        public void failure(RetrofitError error) {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
