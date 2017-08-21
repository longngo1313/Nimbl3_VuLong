package test.simple.com.nimbl3_vulong.presenter;

import android.app.Activity;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.simple.com.nimbl3_vulong.R;
import test.simple.com.nimbl3_vulong.api.ApiClient;
import test.simple.com.nimbl3_vulong.api.ApiInterface;
import test.simple.com.nimbl3_vulong.base.BasePresenter;
import test.simple.com.nimbl3_vulong.data.Question;
import test.simple.com.nimbl3_vulong.data.SurveyCard;
import test.simple.com.nimbl3_vulong.fragments.ListSurveyFragment;

/**
 * Created by Admin on 8/20/2017.
 */

public class FragmentPresenter extends BasePresenter<IFragmentListener> {

    private final static String TAG = ListSurveyFragment.class.getSimpleName();

    /**
     * Token to access data from Web API
     */
    private final static String ACCESS_TOKEN = "cadd366510648bbefdacb0357e4d5e83964a85b2a8bfde17e1458767a1ed914b";

    /**
     * Check value if it is in loading data, avoid loading again
     */
    private boolean mIsLoading = false;

    /**
     * Interface to load data
     */
    private ApiInterface mApiService;

    /**
     * Call Api Retrofit Object
     */
    private Call<List<SurveyCard>> mCall;

    /**
     * List survey data
     */
    private List<SurveyCard> mListSurvey;


    /**
     * Request Data from API
     * @param activity
     */
    public void loadingSurveyList(final Activity activity) {

        if(mIsLoading){
            return;
        }

        mIsLoading = true;

        mListener.showLoadingView();

        mApiService = ApiClient.getClient().create(ApiInterface.class);

        mCall = mApiService.getListSurvey(ACCESS_TOKEN);

        mCall.enqueue(new Callback<List<SurveyCard>>() {
            @Override
            public void onResponse(Call<List<SurveyCard>> call, Response<List<SurveyCard>> response) {
                mListSurvey = response.body();
                if (mListSurvey != null && !mListSurvey.isEmpty()) {
                    mListener.initViewPager(mListSurvey);
                    mListener.showSurveyList();
                    mListener.initIndicator(mListSurvey.size());
                } else {
                    Log.e(TAG, activity.getString(R.string.null_poiter_error_msg));
                }
                mIsLoading = false;
            }

            @Override
            public void onFailure(Call<List<SurveyCard>> call, Throwable throwable) {
                mListener.showErrorText();
                mIsLoading = false;
            }
        });
    }


}
