package com.wuyazhou.learn.picasso.use;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;
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
    public static final String IMAGE_URL_1 = "https://ss.csdn.net/p?https://mmbiz.qpic.cn/mmbiz_jpg/trm5VMeFp9mJejJH2asZZT0ML63erOW3QAMSkjEMsLwByykbJwsHj7QmPbQDDUU43BJpHTXxyiaY24LXlA6zKDQ/640?wx_fmt=jpeg";
    public static final String IMAGE_URL_2 = "http://imgup01.myra2.com/2017-04/13/11/1492055267740_0.jpg";
    public static final String IMAGE_URL_3 = "http://i1.hexun.com/2017-10-20/191306534.jpg";
    public static final String IMAGE_URL_4 = "http://www.pujia8.com/static/pics/20161116120001_22.jpg";
    private Context mContext = null;
    private RelativeLayout mLayout;
    @BindView(R.id.model_button_1)
    Button button01;
    @BindView(R.id.model_button_2)
    Button button02;
    @BindView(R.id.image)
    ImageView mImageView;


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
        Picasso.with(mContext).load(Uri.parse(IMAGE_URL_1)).placeholder(R.drawable.ic_launcher_background).into(mImageView);
    }
    @OnClick(R.id.model_button_2)
    void onClickButton02(){
        LogShowUtil.addLog("Picasso","点击按钮02");
        Picasso.with(mContext).load((IMAGE_URL_2)).error(R.drawable.clear).into(mImageView);
    }
}
