package com.samsung.contentree.application;

import com.samsung.contentree.store.MovieStore;

import android.app.Application;
import android.util.Log;

public class App extends Application {

	public static final String TAG  =App.class.getName();
	private static App sInstance;
	
	private MovieStore mMovieStore;
	
	public static App getInstance(){
		return sInstance;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		sInstance = this;
		//
		makeMovieStore();
	}
	
	public MovieStore getMovieStore(){
		return mMovieStore;
	}
	
	private void makeMovieStore(){
		if(mMovieStore!=null){
			Log.d(TAG, "[makeMovieStore] MovieStore already exist");
			return;
		}
		
		mMovieStore = new MovieStore();
	}

	
	
}
