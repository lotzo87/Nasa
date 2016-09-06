package mx.com.idmexico.vvazquez.nasa.App;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Alumno on 06/08/2016.
 */
public class NasaAplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        FacebookSdk.sdkInitialize(this);
    }
}
