package mx.com.idmexico.vvazquez.nasa.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.Data.ApodService;
import mx.com.idmexico.vvazquez.nasa.Data.Data;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import mx.com.idmexico.vvazquez.nasa.Model.AppDataSource;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;
import mx.com.idmexico.vvazquez.nasa.UI.NasaApodAdapter;
import mx.com.idmexico.vvazquez.nasa.UI.NasaApodFavoriteAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class FavoriteMarsRoverFragment extends Fragment {
    @BindView(R.id.mars_rover_listing)
    RecyclerView marsRoverListingRecycler;
    List<Photo> photo;
    static NasaApodFavoriteAdapter nasaApodFavAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);
    }

    public void fillData()
    {
        AppDataSource source = new AppDataSource(getActivity());
        photo = source.getAllApod();
        nasaApodFavAdapter = new NasaApodFavoriteAdapter();
        nasaApodFavAdapter.setMarsPhotos(photo);
        nasaApodFavAdapter.setOnItemClickListener(new NasaApodFavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Photo photo) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage(R.string.deletemssg).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDataSource source = new AppDataSource(getActivity());
                        source.deleteFavorite(photo.getId());
                        Snackbar.make(getView(), R.string.deleted, Snackbar.LENGTH_SHORT).show();
                        fillData();
                    }
                })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
            }
        });

        marsRoverListingRecycler.setAdapter(nasaApodFavAdapter);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_listing, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        StaggeredGridLayoutManager staggeredGridLayoutManager  =new StaggeredGridLayoutManager(10,StaggeredGridLayoutManager.HORIZONTAL);
        marsRoverListingRecycler.setLayoutManager(gridLayoutManager);
        fillData();

        return view;
    }

}
