package mx.com.idmexico.vvazquez.nasa.Fragments;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.Data.ApodService;
import mx.com.idmexico.vvazquez.nasa.Data.Data;
import mx.com.idmexico.vvazquez.nasa.Model.APOD;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import mx.com.idmexico.vvazquez.nasa.Model.AppDataSource;
import mx.com.idmexico.vvazquez.nasa.Model.Camera;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alumno on 12/08/2016.
 */
public class TodayApodFragment extends Fragment {
    @BindView(R.id.imgVw)
    ImageView imageView;
    @BindView(R.id.txtTitle)
    TextView txtVTitle;
    @BindView(R.id.txtDate) TextView txtVDate;
    @BindView(R.id.txtExplanation) TextView txtVExplanation;
    @BindView(R.id.txtCopyRight) TextView txtVCopyRight;
    private Photo photos;
    Camera camera;
    String url;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main,container, false);
        ButterKnife.bind(this, view);
        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        /*Call<APOD2> callApodService = apodService.getTodayApodPthotoWithQuery(1000,"kG43AiTcKtWf6lYFLjuq0uuBDCdtwmcJtMyireEc");
        callApodService.enqueue(new Callback<APOD2>() {
            @Override
            public void onResponse(Call<APOD2> call, Response<APOD2> response) {

                Picasso.with(getActivity()).load(response.body().getPhotos().get(0).getImgSrc()).into(imageView);
                txtVTitle.setText(response.body().getPhotos().get(0).getRover().getName().toString());
                txtVDate.setText(response.body().getPhotos().get(0).getEarthDate());
                //txtVExplanation.setText(response.body().getExplanation());
                //txtVCopyRight.setText(response.body().getCopyright());
            }

            @Override
            public void onFailure(Call<APOD2> call, Throwable t) {

            }
        });*/
         Call<APOD> callApodService = apodService.getTodayApodWithQuery("kG43AiTcKtWf6lYFLjuq0uuBDCdtwmcJtMyireEc");
        callApodService.enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
                Picasso.with(getActivity()).load(response.body().getUrl()).into(imageView);
                url = response.body().getUrl();
                txtVTitle.setText(response.body().getTitle());
                txtVDate.setText(response.body().getDate());
                txtVExplanation.setText(response.body().getExplanation());
                txtVCopyRight.setText(response.body().getCopyright());
                photos = new Photo();
                photos.setSol(0);
                photos.setImgSrc(response.body().getUrl());
                camera = new Camera();
                camera.setFullName(response.body().getTitle().toString());
                photos.setCamera(camera);
                photos.setEarthDate(response.body().getDate());
            }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_today_apod:
                //Snackbar.make(getView(), R.string.shared, Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),  "Compartido", Toast.LENGTH_LONG).show();
                shareText(url);
                return true;

            case R.id.add_as_favorite:
                /*Snackbar.make(getView(), R.string.added, Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),  "Agregado", Toast.LENGTH_LONG).show();
                return true;*/
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setMessage(R.string.add).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppDataSource appDataSource = new AppDataSource(getActivity());
                        appDataSource.saveAsFavorite(photos);
                        Snackbar.make(getView(), R.string.added, Snackbar.LENGTH_SHORT).show();
                    }
                })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
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
        startActivity(Intent.createChooser(sharedIntent, String.valueOf(R.string.share)));
    }
}
