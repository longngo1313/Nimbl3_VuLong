package test.simple.com.nimbl3.base;

/**
 * Created by NGUYEN VU LONG on 8/21/2017.
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
