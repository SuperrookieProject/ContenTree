package com.samsung.contentree.store;

import android.graphics.Bitmap;

public class Movie {
	public String mTitle;
	public Bitmap mImage;
	public String mCategory;
	public float mRating;
	
	public Movie(String title, Bitmap image, String category,float rating){
		mTitle=title;
		mImage=image;
		mCategory=category;
		mRating=rating;
	}
}
