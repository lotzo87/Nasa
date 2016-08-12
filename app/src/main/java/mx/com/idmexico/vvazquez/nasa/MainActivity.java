package mx.com.idmexico.vvazquez.nasa;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.Data.ApodService;
import mx.com.idmexico.vvazquez.nasa.Data.Data;
import mx.com.idmexico.vvazquez.nasa.Model.APOD;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.imgVw) ImageView imageView;
    @BindView(R.id.txtTitle) TextView txtVTitle;
    @BindView(R.id.txtDate) TextView txtVDate;
    @BindView(R.id.txtExplanation) TextView txtVExplanation;
    @BindView(R.id.txtCopyRight) TextView txtVCopyRight;
    /*ImageView imageView;
    TextView txtVTitle;
    TextView txtVDate;
    TextView txtVExplanation;
    TextView txtVCopyRight;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*imageView = (ImageView)findViewById(R.id.imgVw);
        txtVTitle=  (TextView)findViewById(R.id.txtTitle);
        txtVDate=  (TextView)findViewById(R.id.txtDate);
        txtVExplanation=  (TextView)findViewById(R.id.txtExplanation);
        txtVCopyRight=  (TextView)findViewById(R.id.txtCopyRight);*/
        ButterKnife.bind(this);

        //Log.d("Build Config", BuildConfig.URL);

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        //Call<APOD> callApodService = apodService.getTodayApod();
        /*Call<APOD> callApodService = apodService.getTodayApodWithQuery("kG43AiTcKtWf6lYFLjuq0uuBDCdtwmcJtMyireEc");
        callApodService.enqueue(new Callback<APOD>() {
            @Override
            public void onResponse(Call<APOD> call, Response<APOD> response) {
                Log.d("APOD", response.body().getTitle());
                Picasso.with(getApplicationContext()).load(response.body().getUrl()).into(imageView);
                txtVTitle.setText(response.body().getTitle());
                txtVDate.setText(response.body().getDate());
                txtVExplanation.setText(response.body().getExplanation());
                txtVCopyRight.setText(response.body().getCopyright());
            }

            @Override
            public void onFailure(Call<APOD> call, Throwable t) {

            }
        });*/
        Call<APOD2> callApodService = apodService.getTodayApodPthotoWithQuery(1000,"kG43AiTcKtWf6lYFLjuq0uuBDCdtwmcJtMyireEc");
        callApodService.enqueue(new Callback<APOD2>() {
            @Override
            public void onResponse(Call<APOD2> call, Response<APOD2> response) {

                Picasso.with(getApplicationContext()).load(response.body().getPhotos().get(0).getImgSrc()).into(imageView);
                txtVTitle.setText(response.body().getPhotos().get(0).getRover().getName().toString());
                txtVDate.setText(response.body().getPhotos().get(0).getEarthDate());
                //txtVExplanation.setText(response.body().getExplanation());
                //txtVCopyRight.setText(response.body().getCopyright());
            }

            @Override
            public void onFailure(Call<APOD2> call, Throwable t) {

            }
        });
    }
}
