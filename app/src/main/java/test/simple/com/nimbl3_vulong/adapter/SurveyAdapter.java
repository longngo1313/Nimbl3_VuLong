package test.simple.com.nimbl3_vulong.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import test.simple.com.nimbl3_vulong.R;
import test.simple.com.nimbl3_vulong.data.SurveyCard;
import test.simple.com.nimbl3_vulong.fragments.ListSurveyFragment;

import static test.simple.com.nimbl3_vulong.R.id.survey_desciption;

/**
 * Created by NGUYEN VU LONG on 8/17/2017.
 * FPT Sofware Company Limited
 */

public class SurveyAdapter extends PagerAdapter {

    private Context mContext;

    /**
     * The interface for call back of button Take the survey
     */
    private ListSurveyFragment.ISurveyEvent mButtonEvent;

    /**
     * Data of adapter
     */
    @NonNull
    private List<SurveyCard> mList;

    public SurveyAdapter(Context mContext, List<SurveyCard> mList, ListSurveyFragment.ISurveyEvent mButtonEvent) {
        this.mContext = mContext;
        this.mList = mList;
        this.mButtonEvent = mButtonEvent;
    }

    /**
     * Init data to each pager item
     * @param collection
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.survey_list_item, collection, false);


        TextView mTitle = (TextView) layout.findViewById(R.id.survey_title);
        TextView mDescription = (TextView) layout.findViewById(survey_desciption);
        ImageView mBackGround = (ImageView) layout.findViewById(R.id.list_item_bg);
        Button mButtonSurvey = (Button) layout.findViewById(R.id.take_survey);

        if(mList.get(position)!=null){
            mTitle.setText(mList.get(position).getTitle());
            mDescription.setText(mList.get(position).getDescription());

            String imgUrl = mList.get(position).getCoverImageUrl() + "l";
            Glide.with(mContext).load(imgUrl)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mBackGround);
            mButtonSurvey.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mButtonEvent!=null){
                        mButtonEvent.onSurveyClick(mList.get(position), mList.get(position).getTitle());
                    }
                }
            });

        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }



}
