package com.beyondself.jalen.googleplay.application;

import android.app.Application;
import android.os.Handler;
public class BaseApplication extends Application {
	//获取到主线程的上下文
	private static BaseApplication mContext;
	//获取到主线程的handler
	private static Handler mMainThreadHanler;
	//获取到主线程
	private static Thread mMainThread;
	//获取到主线程的id
	private static int mMainThreadId;
	
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		this.mContext = this;
		this.mMainThreadHanler = new Handler();
		this.mMainThread = Thread.currentThread();
		//获取到调用线程的id
		this.mMainThreadId = android.os.Process.myTid();
	}
	/**获取Application*/
	public static BaseApplication getApplication(){
		return mContext;
	}
	/**获取主线程的Handler*/
	public static Handler getMainThreadHandler(){
		return mMainThreadHanler;
	}
	/**获取主线程 */
	public static Thread getMainThread(){
		return mMainThread;
	}
	/**获取主线程的ID*/
	public static int getMainThreadId(){
		return mMainThreadId;
	}
	
	
	
	
	
	
}
