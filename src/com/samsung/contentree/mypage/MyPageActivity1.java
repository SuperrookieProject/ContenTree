package com.samsung.contentree.mypage;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.samsung.contentree.R;
import com.samsung.contentree.community.CommunityActivity;

public class MyPageActivity1 extends Activity {



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page1);

		//setActionbar
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP|ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_SHOW_TITLE);
		
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("최신등록순");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        Spinner sp = (Spinner)findViewById(R.id.my_page_spinner);
        sp.setPrompt("a");
        sp.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_page, menu);
        return true;
    }
    
    @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.menu_video_community:
			goToVideoCommunity();
			break;

		default:
			break;
		}
    	
		return super.onMenuItemSelected(featureId, item);
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
	
    protected void goToVideoCommunity(){
    	Intent intent = new Intent(this,CommunityActivity.class);
    	startActivity(intent);
    	
    }
}
