package mx.com.idmexico.vvazquez.nasa.Data;

import mx.com.idmexico.vvazquez.nasa.Model.APOD;
import mx.com.idmexico.vvazquez.nasa.Model.APOD2;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alumno on 30/07/2016.
 */
public interface ApodService {
    @GET("planetary/apod?api_key=kG43AiTcKtWf6lYFLjuq0uuBDCdtwmcJtMyireEc")
    Call<APOD> getTodayApod();

    @GET("planetary/apod")
    Call<APOD> getTodayApodWithQuery(@Query("api_key") String apiKey);

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=kG43AiTcKtWf6lYFLjuq0uuBDCdtwmcJtMyireEc")
    Call<APOD2> getTodayApodPthoto();

    @GET("mars-photos/api/v1/rovers/curiosity/photos")
    Call<APOD2> getTodayApodPthotoWithQuery( @Query("sol") int sol,@Query("api_key") String apiKey);

}


