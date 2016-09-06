package mx.com.idmexico.vvazquez.nasa;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.Model.Photo;

/**
 * Created by Alumno on 06/08/2016.
 */
public class DetailActivity extends AppCompatActivity {
    /*@BindView(R.id.detailImage)
    ImageView imageView;
    @BindView(R.id.detailCamera)
    TextView txtCamera;
    @BindView(R.id.detailDate)
    TextView txtDate;*/
    private Bundle bundle;
    Photo photo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        bundle = getIntent().getExtras();
        /*Picasso.with(getApplicationContext()).load(bundle.getString("img")).into(imageView);
        txtCamera.setText(bundle.getString("camera"));
        txtDate.setText(bundle.getString("date"));*/
        //Toast.makeText(getApplicationContext(),  "OK", Toast.LENGTH_LONG).show();
        //Log.d("TAG2", bundle.getString("img").toString());
    }

}
