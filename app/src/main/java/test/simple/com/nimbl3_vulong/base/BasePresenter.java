package test.simple.com.nimbl3_vulong.base;

/**
 * Created by Admin on 8/20/2017.
 */

public abstract class BasePresenter<I extends BaseInterface> {

    /**
     * Interface callback between Presenter and View
     */
    protected I mListener;

    /**
     * Setup Listener for View
     * @param mListener
     */
    public void setListenerToView(I mListener){
        this.mListener = mListener;
    }

}
