package test.simple.com.nimbl3.presenter;

import java.util.List;

import test.simple.com.nimbl3.base.BaseInterface;
import test.simple.com.nimbl3.data.SurveyCard;

/**
 * Created by NGUYEN VU LONG on 8/21/2017.
 */


public interface IFragmentListener extends BaseInterface {

    /**
     * Show loading screen
     */
    void showLoadingView();

    /**
     * Create list of navigation indicator list
     * @param i
     */
    void initIndicator(int i);

    /**
     * Show survey after get from API
     */
    void showSurveyList();

    /**
     * Show error text if loading data failed
     */
    void showErrorText();

    /**
     * Init Data to the view from API
     * @param mListSurvey
     */
    void initViewPager(List<SurveyCard> mListSurvey );
}
