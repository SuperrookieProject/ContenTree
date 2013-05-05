package com.samsung.contentree.community;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import com.samsung.contentree.R;
import com.samsung.contentree.mypage.MyPageActivity1;
import com.samsung.contentree.store.Movie;
import com.samsung.contentree.store.Profile;

import android.R.integer;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore.Action;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CommunityActivity extends Activity {


	private LinearLayout mLaoutTopMovie;
	private LinearLayout mLaoutTopUser;
	private LinearLayout mLaoutRecommendedMovie;
	private LinearLayout mLaoutRecommendedUser;
	private LinearLayout mLaoutRate;
	private Vector mMovieData;
	private Vector mUserData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_community);
		
		//setActionbar
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
		
		//LoadMovies
		if(mMovieData==null){
			loadData();
		}
		
		//TopMoviewViewSetting
		mLaoutTopMovie = (LinearLayout)findViewById(R.id.community_top_movie_inner);
		LayoutInflater inflater = getLayoutInflater();
		HashSet<Integer> indexs  = new HashSet<Integer>();
		while ( indexs.size()<5) {
			Integer index = (int)(Math.random()*mMovieData.size());
			indexs.add(index);
		}
		
		Iterator<Integer> it = indexs.iterator();
		while(it.hasNext()){
			Movie movie = (Movie) mMovieData.get(it.next());
			View item = (View)inflater.inflate(R.layout.item_poster, null);
			ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
			
			poster.setImageBitmap(movie.mImage);
			mLaoutTopMovie.addView(item);
		}

		//TopUserViewSetting
		mLaoutTopUser = (LinearLayout)findViewById(R.id.community_top_user_inner);
		
		indexs = new HashSet<Integer>();
		while ( indexs.size()<3 ) {
			Integer index = (int)(Math.random()*mUserData.size());
			indexs.add(index);
		}
		
		it = indexs.iterator();
		while(it.hasNext()){
			if(mLaoutTopUser.getChildCount()==0){
				View item = (View)inflater.inflate(R.layout.item_poster, null);
				ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
				
				poster.setImageDrawable(getResources().getDrawable(R.drawable.profile_11));
				item.setOnClickListener(mMyListener);
				mLaoutTopUser.addView(item);
			}
			Profile profile = (Profile) mUserData.get(it.next());
			View item = (View)inflater.inflate(R.layout.item_poster, null);
			ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
			
			poster.setImageBitmap(profile.mImage);
			item.setOnClickListener(mMyListener);
			mLaoutTopUser.addView(item);
		}
		
		//RecommendedMovieViewSetting
		mLaoutRecommendedMovie = (LinearLayout)findViewById(R.id.community_recommended_movies_inner);
		
		indexs  = new HashSet<Integer>();
		while ( indexs.size()<5) {
			Integer index = (int)(Math.random()*mMovieData.size());
			indexs.add(index);
		}
		
		it = indexs.iterator();
		while(it.hasNext()){
			Movie movie = (Movie) mMovieData.get(it.next());
			View item = (View)inflater.inflate(R.layout.item_poster, null);
			ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
			
			poster.setImageBitmap(movie.mImage);
			mLaoutRecommendedMovie.addView(item);
		}
		
		//RecommendedUserViewSetting
		mLaoutRecommendedUser = (LinearLayout)findViewById(R.id.community_recommended_users_inner);
		indexs  = new HashSet<Integer>();
		while ( indexs.size()<3) {
			Integer index = (int)(Math.random()*mUserData.size());
			indexs.add(index);
		}
		
		it = indexs.iterator();
		while(it.hasNext()){
			if(mLaoutRecommendedUser.getChildCount()==2){
				View item = (View)inflater.inflate(R.layout.item_poster, null);
				ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
				
				poster.setImageDrawable(getResources().getDrawable(R.drawable.profile_11));
				item.setOnClickListener(mMyListener);
				mLaoutRecommendedUser.addView(item);
			}
			
			Profile profile = (Profile) mUserData.get(it.next());
			View item = (View)inflater.inflate(R.layout.item_poster, null);
			ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
			
			poster.setImageBitmap(profile.mImage);
			item.setOnClickListener(mMyListener);
			mLaoutRecommendedUser.addView(item);
		}
		
		
		//RateViewSetting
		mLaoutRate = (LinearLayout)findViewById(R.id.community_rate_inner);
		
		Iterator it2 = mMovieData.iterator();
		while(it2.hasNext()){
			Movie movie = (Movie)it2.next();
			View item = (View)inflater.inflate(R.layout.item_poster_2, null);
			ImageView poster= (ImageView)item.findViewById(R.id.poster_image_view);
			
			poster.setImageBitmap(movie.mImage);
			mLaoutRate.addView(item);
		}
		
	}

	@Override
	protected void onResume() {		
		//LoadMovies
		if(mMovieData==null){
			loadData();
		}
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_community, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private ItemOnClickListener mMyListener = new ItemOnClickListener();
	
	public class ItemOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(v.getContext(),MyPageActivity1.class);
			startActivity(intent);
		}
	}
	
	public void loadData(){
		mMovieData=new Vector();
		Movie m1= new Movie("ÁÁÀº³ð ³ª»Û³ð ÀÌ»óÇÑ³ð", BitmapFactory.decodeResource(getResources(), R.drawable.poster_1), "Action", 5);
		mMovieData.add(m1);
		Movie m2= new Movie("The pianist", BitmapFactory.decodeResource(getResources(), R.drawable.poster_2), "Action", 5);
		mMovieData.add(m2);
		Movie m3= new Movie("¿ÕÀÇ ³²ÀÚ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_3), "Action", 5);
		mMovieData.add(m3);
		Movie m4= new Movie("ÇÏ¿ï¸µ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_4), "Action", 5);
		mMovieData.add(m4);
		Movie m5= new Movie("ÁÖ¸Ô¿Õ ¶öÇÁ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_5), "Action", 5);
		mMovieData.add(m5);
		Movie m6= new Movie("Iron man", BitmapFactory.decodeResource(getResources(), R.drawable.poster_6), "Action", 5);
		mMovieData.add(m6);
		Movie m7= new Movie("Twilight", BitmapFactory.decodeResource(getResources(), R.drawable.poster_7), "Action", 5);
		mMovieData.add(m7);
		Movie m8= new Movie("Star trek", BitmapFactory.decodeResource(getResources(), R.drawable.poster_8), "Action", 5);
		mMovieData.add(m8);
		Movie m9= new Movie("Les Misrables", BitmapFactory.decodeResource(getResources(), R.drawable.poster_9), "Action", 5);
		mMovieData.add(m9);
		Movie m10= new Movie("127½Ã°£", BitmapFactory.decodeResource(getResources(), R.drawable.poster_10), "Action", 5);
		mMovieData.add(m10);
		Movie m11= new Movie("ÁÁÀº³ð ³ª»Û³ð ÀÌ»óÇÑ³ð", BitmapFactory.decodeResource(getResources(), R.drawable.poster_11), "Action", 5);
		mMovieData.add(m11);
		Movie m12= new Movie("The pianist", BitmapFactory.decodeResource(getResources(), R.drawable.poster_12), "Action", 5);
		mMovieData.add(m12);
		Movie m13= new Movie("¿ÕÀÇ ³²ÀÚ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_13), "Action", 5);
		mMovieData.add(m13);
		Movie m14= new Movie("ÇÏ¿ï¸µ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_14), "Action", 5);
		mMovieData.add(m14);
		Movie m15= new Movie("ÁÖ¸Ô¿Õ ¶öÇÁ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_15), "Action", 5);
		mMovieData.add(m15);
		Movie m16= new Movie("Iron man", BitmapFactory.decodeResource(getResources(), R.drawable.poster_16), "Action", 5);
		mMovieData.add(m16);
		Movie m17= new Movie("Twilight", BitmapFactory.decodeResource(getResources(), R.drawable.poster_17), "Action", 5);
		mMovieData.add(m17);
		Movie m18= new Movie("Star trek", BitmapFactory.decodeResource(getResources(), R.drawable.poster_18), "Action", 5);
		mMovieData.add(m18);
		Movie m19= new Movie("Les Misrables", BitmapFactory.decodeResource(getResources(), R.drawable.poster_19), "Action", 5);
		mMovieData.add(m19);
		Movie m20= new Movie("127½Ã°£", BitmapFactory.decodeResource(getResources(), R.drawable.poster_20), "Action", 5);
		mMovieData.add(m20);
		Movie m21= new Movie("ÁÁÀº³ð ³ª»Û³ð ÀÌ»óÇÑ³ð", BitmapFactory.decodeResource(getResources(), R.drawable.poster_21), "Action", 5);
		mMovieData.add(m21);
		Movie m22= new Movie("The pianist", BitmapFactory.decodeResource(getResources(), R.drawable.poster_22), "Action", 5);
		mMovieData.add(m22);
		Movie m23= new Movie("¿ÕÀÇ ³²ÀÚ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_23), "Action", 5);
		mMovieData.add(m23);
		Movie m24= new Movie("ÇÏ¿ï¸µ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_24), "Action", 5);
		mMovieData.add(m24);
		Movie m25= new Movie("ÁÖ¸Ô¿Õ ¶öÇÁ", BitmapFactory.decodeResource(getResources(), R.drawable.poster_25), "Action", 5);
		mMovieData.add(m25);
		Movie m26= new Movie("Iron man", BitmapFactory.decodeResource(getResources(), R.drawable.poster_26), "Action", 5);
		mMovieData.add(m26);
		Movie m27= new Movie("Twilight", BitmapFactory.decodeResource(getResources(), R.drawable.poster_27), "Action", 5);
		mMovieData.add(m27);
		Movie m28= new Movie("Star trek", BitmapFactory.decodeResource(getResources(), R.drawable.poster_28), "Action", 5);
		mMovieData.add(m28);
		Movie m29= new Movie("Les Misrables", BitmapFactory.decodeResource(getResources(), R.drawable.poster_29), "Action", 5);
		mMovieData.add(m29);
		Movie m30= new Movie("127½Ã°£", BitmapFactory.decodeResource(getResources(), R.drawable.poster_30), "Action", 5);
		mMovieData.add(m30);
		
		

		mUserData=new Vector();
		Profile p1= new Profile("Albert", BitmapFactory.decodeResource(getResources(), R.drawable.profile_01));
		mUserData.add(p1);
		Profile p2= new Profile("Ferdinand", BitmapFactory.decodeResource(getResources(),R.drawable.profile_02));
		mUserData.add(p2);
		Profile p3= new Profile("Gabriel", BitmapFactory.decodeResource(getResources(),R.drawable.profile_03));
		mUserData.add(p3);
		Profile p4= new Profile("Leonard", BitmapFactory.decodeResource(getResources(),R.drawable.profile_04));
		mUserData.add(p4);
		Profile p5= new Profile("Patrick", BitmapFactory.decodeResource(getResources(),R.drawable.profile_05));
		mUserData.add(p5);
		Profile p6= new Profile("Vivian", BitmapFactory.decodeResource(getResources(),R.drawable.profile_06));
		mUserData.add(p6);
		Profile p7= new Profile("Aileen", BitmapFactory.decodeResource(getResources(),R.drawable.profile_07));
		mUserData.add(p7);
		Profile p8= new Profile("Naomi", BitmapFactory.decodeResource(getResources(),R.drawable.profile_08));
		mUserData.add(p8);
		Profile p9= new Profile("Sophia", BitmapFactory.decodeResource(getResources(),R.drawable.profile_09));
		mUserData.add(p9);
		Profile p10= new Profile("Beatrice", BitmapFactory.decodeResource(getResources(),R.drawable.profile_10));
		mUserData.add(p10);
	}
}
