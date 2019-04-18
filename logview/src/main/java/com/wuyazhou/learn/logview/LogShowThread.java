package com.wuyazhou.learn.logview;

import java.util.concurrent.BlockingQueue;

/**
 * @author wuyazhou
 * @function 测试while true为什么没有永远执行
 * */
public class LogShowThread extends Thread{
    private final BlockingQueue<LogShowView.LogModel> mBlockingQueue;
    private LogShowViewContract mShowLogViewContract;
    private boolean mQuit = false;


    public LogShowThread(BlockingQueue<LogShowView.LogModel> mBlockingQueue){
        this.mBlockingQueue = mBlockingQueue;
    }

    public void start(LogShowViewContract showLogViewContract){
        super.start();
        this.mShowLogViewContract = showLogViewContract;
        mQuit = false;
    }

    public void pause() {
        mQuit = true;
        interrupted();
    }

    public void quit() {
        mShowLogViewContract = null;
        mQuit = true;
        interrupted();
    }

    @Override
    public void run() {
        while (!isInterrupted()){
            try {
                if (mShowLogViewContract == null){
                    return;
                }
                LogShowView.LogModel logModel = mBlockingQueue.take();
                mShowLogViewContract.showLog(logModel.key,logModel.value);
            } catch (InterruptedException e) {
                if (mQuit) {
                    return;
                }
                continue;
            }
        }
    }
}
