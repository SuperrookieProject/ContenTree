package com.samsung.contentree.mypage;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.samsung.contentree.R;

public class MyPageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
        
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
	
}
