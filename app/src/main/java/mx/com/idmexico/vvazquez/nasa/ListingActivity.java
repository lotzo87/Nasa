package mx.com.idmexico.vvazquez.nasa;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.App.NasaAplication;
import mx.com.idmexico.vvazquez.nasa.Fragments.FavoriteMarsRoverFragment;
import mx.com.idmexico.vvazquez.nasa.Fragments.MarsRoverFragment;
import mx.com.idmexico.vvazquez.nasa.Fragments.TodayApodFragment;

/**
 * Created by Alumno on 05/08/2016.
 */
public class ListingActivity extends AppCompatActivity{
    //@BindView(R.id.mars_rover_listing)
    //RecyclerView marsRoverListingRecycler;
    @BindView(R.id.listing_toolbar)
    Toolbar toolbar;
    @BindView(R.id.listing_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.listing_navigation_drawer)
    DrawerLayout drawerLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_listing);
        setContentView(R.layout.navigation_drawer);
        ButterKnife.bind(this);
        getFBUserInfo();


        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()){
                    case R.id.today_apod_item:
                        Snackbar.make(findViewById(android.R.id.content),R.string.today, Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.Contenedor,new TodayApodFragment() ).addToBackStack("TodayBack").commit();
                        return true;
                    case R.id.mars_rover_item:
                        Snackbar.make(findViewById(android.R.id.content), R.string.rover, Snackbar.LENGTH_SHORT).show();
                        getFragmentManager().beginTransaction().replace(R.id.Contenedor,new MarsRoverFragment() ).addToBackStack("RoverBack").commit();
                        return true;
                    case R.id.favaorite_item:
                        Snackbar.make(findViewById(android.R.id.content), R.string.favorite, Snackbar.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().replace(R.id.Contenedor, new FavoriteMarsRoverFragment()).addToBackStack("FavBack").commit();
                        return true;
                    case R.id.logout_item:
                        if(AccessToken.getCurrentAccessToken()!=null)
                            LoginManager.getInstance().logOut();
                        finish();
                        return true;
                    default:
                        return false;
                }
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
                intent.putExtra("img", photo.getImgSrc().toString()).putExtra("camera", photo.getCamera().getFullName()).putExtra("date", photo.getEarthDate());
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
        });*/
    }

    private void getFBUserInfo()
    {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    Log.d("name", object.getString("name"));
                    Log.d("id", object.getString("id"));
                    SimpleDraweeView userImage = (SimpleDraweeView)findViewById(R.id.userImage);
                    userImage.setImageURI("http://graph.facebook.com/"+ object.getString("id")+"/picture?type=large");
                    RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                    roundingParams.setBorder(R.color.colorRed, 1);
                    roundingParams.setRoundAsCircle(true);
                    userImage.getHierarchy().setRoundingParams(roundingParams);
                    TextView userName = (TextView) findViewById(R.id.userName);
                    userName.setText(object.getString("name"));
                    TextView userEmail = (TextView) findViewById(R.id.userEmail);
                    userEmail.setText(object.getString("email"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();
    }
}
