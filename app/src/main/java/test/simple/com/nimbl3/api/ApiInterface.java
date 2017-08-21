package test.simple.com.nimbl3.api;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.simple.com.nimbl3.data.SurveyCard;

/**
 * Created by NGUYEN VU LONG on 8/21/2017.
 */

public interface ApiInterface {
    @GET("surveys.json?page=1&per_page=10&")
    Call<List<SurveyCard>> getListSurvey(@Query("access_token") String apiKey);
}
