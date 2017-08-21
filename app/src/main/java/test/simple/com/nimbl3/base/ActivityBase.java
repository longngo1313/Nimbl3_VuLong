package test.simple.com.nimbl3.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NGUYEN VU LONG on 8/21/2017.
 */
public abstract class ActivityBase<T extends BasePresenter, I extends BaseInterface> extends AppCompatActivity {

    /**
     * The presenter attached to this view
     */
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        mPresenter.setListenerToView(setListener());
        initView();
    }


    /**
     * Init child views and actions into parent view
     */
    protected abstract void initView();

    /**
     * Set up presenter to view
     * @return
     */
    protected abstract T setPresenter();

    /**
     * Set up listener between presenter and view
     * @return
     */
    protected abstract I setListener();
}
