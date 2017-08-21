package test.simple.com.nimbl3_vulong.presenter;

import java.util.List;

import test.simple.com.nimbl3_vulong.base.BaseInterface;
import test.simple.com.nimbl3_vulong.data.SurveyCard;

/**
 * Created by Admin on 8/20/2017.
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
