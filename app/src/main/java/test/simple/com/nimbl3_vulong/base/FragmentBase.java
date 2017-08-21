package test.simple.com.nimbl3_vulong.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 8/20/2017.
 */

public abstract class FragmentBase<T extends BasePresenter, I extends BaseInterface> extends Fragment {

    /**
     * The presenter attached to this view
     */
    protected T mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mPresenter = setPresenter();
        mPresenter.setListenerToView(setListener());
        View v = initView(inflater, container, savedInstanceState);
        return v;
    }

    /**
     * Init child views and actions into parent view
     */
    protected abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState);

    /**
     * Set up presenter to view
     * @return
     */
    @NonNull
    protected abstract T setPresenter();

    /**
     * Set up listener between presenter and view
     * @return
     */
    protected abstract I setListener();
}
