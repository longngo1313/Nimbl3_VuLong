package test.simple.com.nimbl3.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.simple.com.nimbl3.R;
import test.simple.com.nimbl3.adapter.SurveyAdapter;
import test.simple.com.nimbl3.base.FragmentBase;
import test.simple.com.nimbl3.customviews.VerticalViewPager;
import test.simple.com.nimbl3.data.SurveyCard;
import test.simple.com.nimbl3.presenter.FragmentPresenter;
import test.simple.com.nimbl3.presenter.IFragmentListener;

/**
 * Created by NGUYEN VU LONG on 8/21/2017.
 */

public class ListSurveyFragment extends FragmentBase<FragmentPresenter, IFragmentListener> implements ViewPager.OnPageChangeListener {

    /**
     * List survey view
     */
    private ViewPager mSurveyPager;

    /**
     * Adapter for rent survey data to view pager
     */
    private SurveyAdapter mAdapter;

    /**
     * Loading data view screen
     */
    private LinearLayout mLoadingLayout;

    /**
     * Error messenger layout
     */
    private TextView mErrorText;

    /**
     * List bullets of navigation indicator
     */
    private List<ImageView> imageViewIndicatorList = null;

    /**
     * Navigation indicator layout container
     */
    private LinearLayout linearLayoutIndicator;

    /**
     * Callback to activity of button action
     */
    private ListSurveyFragment.ISurveyEvent mSurveyEvent;

    public static ListSurveyFragment newInstance() {
        ListSurveyFragment myFragment = new ListSurveyFragment();
        return myFragment;
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_survey_fragmnet, container, false);

        mSurveyPager = (VerticalViewPager) view.findViewById(R.id.survey_pager);
        linearLayoutIndicator = (LinearLayout) view.findViewById(R.id.indicator_ll);
        mLoadingLayout = (LinearLayout) view.findViewById(R.id.progress_bar);
        mErrorText = (TextView) view.findViewById(R.id.error_messenger);

        //Listening the changing event in order to change bullet of navigation indicator list
        mSurveyPager.addOnPageChangeListener (this);

        //Request data from API
        mPresenter.loadingSurveyList(getActivity());
        return view;
    }


    @NonNull
    @Override
    protected FragmentPresenter setPresenter() {
        return new FragmentPresenter();
    }

    @Override
    protected IFragmentListener setListener() {
        return new IFragmentListener() {

            @Override
            public void showLoadingView() {
                mSurveyPager.setVisibility(View.INVISIBLE);
                mErrorText.setVisibility(View.INVISIBLE);
                mLoadingLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void initIndicator(int sizePager) {
                if(getActivity()==null){
                    return;
                }
                imageViewIndicatorList = new ArrayList<>();

                linearLayoutIndicator.removeAllViews();
                linearLayoutIndicator.setOrientation(LinearLayout.VERTICAL);

                linearLayoutIndicator.setPadding(dp2px(getActivity(), 320), 0, 0, 0);
                linearLayoutIndicator.setGravity(Gravity.CENTER);


                if(sizePager == 0){
                    return;
                }

                for (int i = 0; i < sizePager; i++) {
                    ImageView iv = new ImageView(getActivity());
                    iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));

                    iv.setPadding(0, dp2px(getActivity(), 10), 0, 0);
                    if (i == 0) {
                        iv.setImageResource(R.drawable.shape_sel);
                    } else {
                        iv.setImageResource(R.drawable.shape_nor);
                    }
                    imageViewIndicatorList.add(iv);
                    linearLayoutIndicator.addView(iv);
                }
            }

            @Override
            public void showSurveyList() {
                mErrorText.setVisibility(View.INVISIBLE);
                mLoadingLayout.setVisibility(View.INVISIBLE);
                mSurveyPager.setVisibility(View.VISIBLE);
            }

            @Override
            public void showErrorText() {
                mSurveyPager.setVisibility(View.INVISIBLE);
                mLoadingLayout.setVisibility(View.INVISIBLE);
                mErrorText.setVisibility(View.VISIBLE);
            }

            @Override
            public void initViewPager(List<SurveyCard> mListSurvey ){
                //Load data to the view pager
                if (mListSurvey != null && !mListSurvey.isEmpty()) {
                    mAdapter = new SurveyAdapter(getActivity(), mListSurvey, mSurveyEvent);
                    mSurveyPager.setAdapter(mAdapter);
                }
            }
        };
    }

    /**
     * Convert dp value to pixel
     * * @param paramContext
     * @param paramFloat
     * @return
     */
    public static int dp2px(Context paramContext, float paramFloat) {
        DisplayMetrics localDisplayMetrics = paramContext.getResources()
                .getDisplayMetrics();
        return (int) TypedValue.applyDimension(1, paramFloat,
                localDisplayMetrics);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * Change bullet in the  navigation indicator list each time user scroll
     * @param position
     */
    @Override
    public void onPageSelected(int position) {

        if(imageViewIndicatorList == null || imageViewIndicatorList.isEmpty()){
            return;
        }
        for (int i = 0; i < imageViewIndicatorList.size(); i++) {
            if (position == i) {
                imageViewIndicatorList.get(i).setImageResource(
                        R.drawable.shape_sel);
            } else {
                imageViewIndicatorList.get(i).setImageResource(
                        R.drawable.shape_nor);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


    /**
     * Set Call back to the parent activity
     * @param onSurveyClinkEvent
     */
    public void setOnSurveyClickEvent(ListSurveyFragment.ISurveyEvent onSurveyClinkEvent){
        this.mSurveyEvent = onSurveyClinkEvent;
    }

    /**
     * Interface to communicate with activity
     */
    public interface ISurveyEvent {
        void onSurveyClick(SurveyCard surveyCard, String title);
    }

    /**
     * Refresh data function
     */
    public void reloadSurveyList(){
        if(mPresenter!=null){
            mPresenter.loadingSurveyList(getActivity());
        }
    }

}
