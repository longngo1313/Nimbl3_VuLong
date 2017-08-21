package test.simple.com.nimbl3_vulong.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NGUYEN VU LONG on 8/16/2017.
 * FPT Sofware Company Limited
 */

public class ApiClient {


    public static final String BASE_URL = "https://nimbl3-survey-api.herokuapp.com/";

    private static Retrofit retrofit = null;

    /**
     * Get retrofit object
     * @return
     */
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
