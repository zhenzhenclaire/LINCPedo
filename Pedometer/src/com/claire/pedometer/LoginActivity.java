package com.claire.pedometer;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class LoginActivity extends Activity {
	private TextView tView;
	
	public void init() {
		tView = (TextView) findViewById(R.id.textView1);
        tView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ComponentName componentname = new ComponentName(LoginActivity.this, "com.claire.pedometer.RegisterActivity.java");
				Intent intent = new Intent();
				intent.setComponent(componentname);
				startActivity(intent);
			}
		});
	}
	 protected void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);   

	        setContentView(R.layout.activity_login);  
	     
	        
	        
	      
	    }  
	    


}
