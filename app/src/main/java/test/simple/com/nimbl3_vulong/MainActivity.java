package test.simple.com.nimbl3_vulong;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import test.simple.com.nimbl3_vulong.data.SurveyCard;
import test.simple.com.nimbl3_vulong.fragments.DetailSurveyFragment;
import test.simple.com.nimbl3_vulong.fragments.ListSurveyFragment;
import test.simple.com.nimbl3_vulong.ultis.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Toolbar with refresh/back button
     */
    private Toolbar mToolbar;

    /**
     * Refresh/back button
     */
    private ImageButton mRefreshBtn;

    /**
     * Fragment show list survey
     */
    private ListSurveyFragment mListFragment;

    /**
     * Fragment showing detail of list survey's item
     */
    private DetailSurveyFragment mDetailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        mRefreshBtn = (ImageButton) findViewById(R.id.btn_refresh);
        setSupportActionBar(mToolbar);

        FragmentManager fragmentManager = getFragmentManager();

        mListFragment = ListSurveyFragment.newInstance();

        fragmentManager.beginTransaction().replace(R.id.fragment_container, mListFragment).commit();

        mRefreshBtn.setOnClickListener(this);

        //Handle on "Take a survey" button event
        mListFragment.setOnSurveyClickEvent(new ListSurveyFragment.ISurveyEvent() {
            @Override
            public void onSurveyClick(SurveyCard survey, String title) {
                mDetailFragment = DetailSurveyFragment.newInstance(survey, title);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .setCustomAnimations(R.animator.fragment_in, R.animator.fragment_out)
                        .replace(R.id.detail_fragment_container, mDetailFragment).commit();
                mRefreshBtn.setImageResource(R.drawable.back_button_bg);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_refresh:
                if(mDetailFragment !=null){
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.animator.fragment_in, R.animator.fragment_out)
                            .remove(mDetailFragment).commit();
                    mRefreshBtn.setImageResource(R.drawable.refresh_button_bg);
                    mDetailFragment = null;
                }else if(mListFragment!=null){
                    if(!Utils.checkNetworkConnectStatus(getApplicationContext())){
                        createNetErrorDialog();
                    }else {
                        mListFragment.reloadSurveyList();
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * Show error internet if need
     */
    protected void createNetErrorDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.wifi_not_connect_messenger)
                .setTitle(R.string.wifi_not_connect_title)
                .setCancelable(false)
                .setPositiveButton(R.string.setting_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                startActivity(i);
                            }
                        }
                )
                .setNegativeButton(R.string.cancel_text,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        }
                );
        AlertDialog alert = builder.create();
        alert.show();
    }

}
