package test.simple.com.nimbl3_vulong.api;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.simple.com.nimbl3_vulong.data.Question;
import test.simple.com.nimbl3_vulong.data.SurveyCard;

/**
 * Created by NGUYEN VU LONG on 8/16/2017.
 * FPT Sofware Company Limited
 */

public interface ApiInterface {
    @GET("surveys.json?page=1&per_page=10&")
    Call<List<SurveyCard>> getListSurvey(@Query("access_token") String apiKey);
}
