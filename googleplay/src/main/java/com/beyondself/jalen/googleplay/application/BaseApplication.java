package com.beyondself.jalen.googleplay.application;

import android.app.Application;
import android.os.Handler;

/**
 * =========================================================
 *
 *  版权: 个人开发Mr.Jalen  版权所有(c) ${YEAR}

 *  作者:${USER}

 *  版本: 1.0
 *
 *
 *  创建日期 : ${DATE}  ${HOUR}:${MINUTE}
 *
 *
 *  邮箱：Studylifetime@sina.com
 *
 *  描述:
 *	 可以让整个应用都获得相应的东西
 *
 *  修订历史:
 *
 * =========================================================
 */
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
		super.onCreate();
		initSDK();
		this.mContext = this;
		this.mMainThreadHanler = new Handler();
		this.mMainThread = Thread.currentThread();
		//获取到调用线程的id
		this.mMainThreadId = android.os.Process.myTid();
	}

	private void initSDK() {
//		x.Ext.init(this);
//		x.Ext.setDebug(true); // 是否输出debug日志
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
