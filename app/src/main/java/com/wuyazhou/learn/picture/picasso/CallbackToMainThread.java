package com.wuyazhou.learn.picture.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author wuyzh
 * @fuction 将返回结果直接回调给UI线程，整个方法都在UI线程内执行
 * CallbackToMainThread实现自Callback，Callback是OkHttp3请求回调要实现的接口
 * */
public class CallbackToMainThread implements Callback {
    private Callback mCallback = null;
    private Call mCall = null;
    private IOException mIOException = null;
    private String mStringResponse = null;
    private Bitmap mBitmapResponse = null;
    /**
     * @param callback 这里是请求结果的回调，其回调方法将会在UI线程内执行
     * */
    public CallbackToMainThread(Callback callback){
        mCallback = callback;
    }
    /**
     * 此处new android.os.Handler(Looper.getMainLooper()).post(runnableFailureUI)是将失败通过UI线程返回
     * */
    @Override
    public void onFailure(Call call, IOException e) {
        mCall = call;
        mIOException = e;
        new android.os.Handler(Looper.getMainLooper()).post(runnableFailureUI);
    }

    /**
     * 这里会根据主线程内Callback的getTClassName实现来获取想要得到的数据类型，然后去做类型转化
     * */
    @Override
    public void onResponse(Call call, Response response) {
        mCall = call;
        try {
            if (mCallback.getTClassName().equals(String.class.getName())){
                mStringResponse = response.body().string();
            }else if (mCallback.getTClassName().equals(Bitmap.class.getName())){
                byte[] data = response.body().bytes();
                BitmapFactory.Options decodeOptions = new BitmapFactory.Options();

                decodeOptions.inPreferredConfig = Bitmap.Config.RGB_565;
                mBitmapResponse = BitmapFactory.decodeByteArray(data, 0, data.length, decodeOptions);
            }
        } catch (IOException e) {
            mIOException = e;
            new android.os.Handler(Looper.getMainLooper()).post(runnableFailureUI);
        }
        new android.os.Handler(Looper.getMainLooper()).post(runnableSuccessUI);
    }
    Runnable runnableFailureUI = new Runnable() {
        @Override
        public void run() {
            mCallback.onFailure(mCall,mIOException);
        }
    };

    Runnable runnableSuccessUI = new Runnable() {
        @Override
        public void run() {
            try {
                if (mStringResponse != null){
                    mCallback.onResponse(mCall,mStringResponse);
                }else if (mBitmapResponse != null){
                    mCallback.onResponse(mCall,mBitmapResponse);
                }

            } catch (IOException e) {
                mCallback.onFailure(mCall,e);
            }
        }
    };

    /**
     * 此处接口将会在UI线程内实现
     * 成功失败都将会通过这里面的方法返回给UI线程
     * */
    public interface Callback<T> {
        void onFailure(Call call, IOException e);
        void onResponse(Call call, T response) throws IOException;
        String getTClassName();
    }
}
