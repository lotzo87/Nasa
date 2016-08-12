package mx.com.idmexico.vvazquez.nasa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.Data.ApodService;
import mx.com.idmexico.vvazquez.nasa.Data.Data;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.UI.NasaApodAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 05/08/2016.
 */
public class ListingActivity extends AppCompatActivity{
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        StaggeredGridLayoutManager staggeredGridLayoutManager  =new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.HORIZONTAL);
        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Log.d("TAG", photo.getImgSrc());
                Toast.makeText(getApplicationContext(),  photo.getImgSrc().toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("img",photo.getImgSrc()).putExtra("camera",photo.getRover().getName().toString()).putExtra("date",photo.getEarthDate().toString());
                startActivity(intent);
            }
        });

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        //apodService.getTodayApodPthotoWithQuery(1000,BuildConfig.URL).enqueue(new Callback<APOD2>() {
        apodService.getTodayApodPthoto().enqueue(new Callback<APOD2>() {
            @Override
            public void onResponse(Call<APOD2> call, Response<APOD2> response) {
                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                //marsRoverListingRecycler.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                marsRoverListingRecycler.setAdapter(nasaApodAdapter);
            }

            @Override
            public void onFailure(Call<APOD2> call, Throwable t) {

            }
        });
    }
}
