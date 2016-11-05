package by.onliner.flatsapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import by.onliner.flatsapp.R;
import by.onliner.flatsapp.model.Apartment;
import by.onliner.flatsapp.network.API;
import by.onliner.flatsapp.network.Response.ApartmentsResponse;
import by.onliner.flatsapp.ui.FragmentUtils;
import by.onliner.flatsapp.ui.fragment.FlatsFragment;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentUtils.replaceContent(this, R.id.content_main, new FlatsFragment(), FlatsFragment.TAG);
    }

}
