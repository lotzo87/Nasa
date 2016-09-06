package mx.com.idmexico.vvazquez.nasa.Login;

import android.content.Intent;
import android.hardware.camera2.params.Face;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.com.idmexico.vvazquez.nasa.App.NasaAplication;
import mx.com.idmexico.vvazquez.nasa.DetailActivity;
import mx.com.idmexico.vvazquez.nasa.Fragments.MarsRoverFragment;
import mx.com.idmexico.vvazquez.nasa.Fragments.TodayApodFragment;
import mx.com.idmexico.vvazquez.nasa.ListingActivity;
import mx.com.idmexico.vvazquez.nasa.MainActivity;
import mx.com.idmexico.vvazquez.nasa.R;

/**
 * Created by Alumno on 13/08/2016.
 */
public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {
    @BindView(R.id.fblogin_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Log.d("TAG: ", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //Log.d("TAG: ", "CallbackManager");
        loginButton.setReadPermissions("email");
        callbackManager  = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, this);

        //Log.d("TAG: ","onCreate() valida sesión");
        if(AccessToken.getCurrentAccessToken()!= null)
            //Snackbar.make(findViewById(android.R.id.content), R.string.login, Snackbar.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), ListingActivity.class));
    }

    @Override
    public void onSuccess(LoginResult loginResult) {
        //Log.d("TAG: ","onSuccess");
        Snackbar.make(findViewById(android.R.id.content), R.string.login, Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), ListingActivity.class));
    }

    @Override
    public void onCancel() {
        //Log.d("TAG: ","onCancel");
        Snackbar.make(findViewById(android.R.id.content), R.string.logincancelled, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        //Log.d("TAG: ","onError");
        Snackbar.make(findViewById(android.R.id.content), error.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Log.d("TAG: ","onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
