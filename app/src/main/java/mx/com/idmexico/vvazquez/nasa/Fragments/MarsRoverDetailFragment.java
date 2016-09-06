package mx.com.idmexico.vvazquez.nasa.Fragments;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;
import mx.com.idmexico.vvazquez.nasa.Data.ApodService;
import mx.com.idmexico.vvazquez.nasa.Data.Data;
import mx.com.idmexico.vvazquez.nasa.DetailActivity;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import mx.com.idmexico.vvazquez.nasa.Model.AppDataSource;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;
import mx.com.idmexico.vvazquez.nasa.R;
import mx.com.idmexico.vvazquez.nasa.UI.NasaApodAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sistemas on 18/08/2016.
 */
public class MarsRoverDetailFragment extends Fragment {
    @BindView(R.id.detail_image)
    ImageView imageView;
    @BindView(R.id.detail_camera)
    TextView txtCamera;
    @BindView(R.id.detail_date)
    TextView txtDate;
    private Bundle bundle;
    private static Photo photos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    public static MarsRoverDetailFragment newFragmentDetail(Photo photo)
    {
        MarsRoverDetailFragment marsRoverDetailFragment = new MarsRoverDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("img", photo.getImgSrc());
        bundle.putString("camera", photo.getCamera().getFullName());
        bundle.putString("date", photo.getEarthDate());
        bundle.putInt("sol", photo.getSol());
        marsRoverDetailFragment.setArguments(bundle);
        photos = photo;
        return marsRoverDetailFragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_detail, container, false);
        ButterKnife.bind(this, view);
        bundle = getArguments();
        Picasso.with(getActivity()).load(bundle.getString("img")).into(imageView);
        txtCamera.setText(bundle.getString("camera"));
        txtDate.setText(bundle.getString("date"));
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_today_apod:
                //Snackbar.make(getView(), R.string.shared, Snackbar.LENGTH_SHORT).show();
                //Toast.makeText(getActivity(),  "Compartido", Toast.LENGTH_LONG).show();
                shareText(photos.getImgSrc());
                return true;

            case R.id.add_as_favorite:
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
                Log.d("TAG:", "Entra default");
                return super.onOptionsItemSelected(item);

        }
    }

    public void shareText(String path) {
        Intent sharedIntent = new Intent(Intent.ACTION_SEND);
        sharedIntent.setType("text/plain");
        sharedIntent.putExtra(Intent.EXTRA_TEXT, path);
        startActivity(Intent.createChooser(sharedIntent, String.valueOf(R.string.share)));
    }
}
