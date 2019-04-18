package com.wuyazhou.learn.picasso.use;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.wuyazhou.learn.logview.LogShowUtil;
import com.wuyazhou.learn.picasso.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author 吴亚洲
 * @date 2018.7.7
 * @function
 */
public class PicassoUsePagerView extends FrameLayout {
    private Context mContext = null;
    private RelativeLayout mLayout;
    @BindView(R.id.model_button_1)
    Button button01;
    @BindView(R.id.model_button_2)
    Button button02;


    public PicassoUsePagerView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public PicassoUsePagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public PicassoUsePagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    public void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mLayout = (RelativeLayout) inflater.inflate(R.layout.pager_model_layout, null);

        addView(mLayout);

        ButterKnife.bind(this,mLayout);
    }
    @OnClick(R.id.model_button_1)
    void onClickButton01(){
        LogShowUtil.addLog("Picasso","点击按钮01");
    }
    @OnClick(R.id.model_button_2)
    void onClickButton02(){
        LogShowUtil.addLog("Picasso","点击按钮02");
    }
}
