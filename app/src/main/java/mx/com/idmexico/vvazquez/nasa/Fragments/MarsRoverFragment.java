package mx.com.idmexico.vvazquez.nasa.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.Data.ApodService;
import mx.com.idmexico.vvazquez.nasa.Data.Data;
import mx.com.idmexico.vvazquez.nasa.DetailActivity;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;
import mx.com.idmexico.vvazquez.nasa.UI.NasaApodAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class MarsRoverFragment extends Fragment {
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setHasOptionsMenu(true);*/
    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_listing, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        StaggeredGridLayoutManager staggeredGridLayoutManager  =new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.HORIZONTAL);
        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();
        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {
                Log.d("TAG", photo.getImgSrc());
                Toast.makeText(getActivity(),  photo.getImgSrc().toString(), Toast.LENGTH_LONG).show();
                /*Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("img", photo.getImgSrc().toString()).putExtra("camera", photo.getCamera().getFullName()).putExtra("date", photo.getEarthDate());
                startActivity(intent);*/

                getFragmentManager().beginTransaction().replace(R.id.Contenedor, MarsRoverDetailFragment.newFragmentDetail(photo)).addToBackStack("").commit();//.getImgSrc().toString(),photo.getCamera().getFullName(),photo.getEarthDate())).commit();
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
        return view;
    }

    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_today_apod:
                //Snackbar.make(getView(), R.string.shared, Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),  "Compartido", Toast.LENGTH_LONG).show();
                shareText("Compartir");
                return true;

            case R.id.add_as_favorite:
                Snackbar.make(getView(), R.string.compartido, Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),  "Agregado", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void shareText(String text)
    {
        Intent sharedIntent = new Intent(Intent.ACTION_SEND);
        sharedIntent.setType("text/plain");
        sharedIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(sharedIntent, "Compartir"));
    }*/

}
