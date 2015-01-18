package com.shoichi.dominator;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnLongClickListener;


@SuppressWarnings("deprecation")
public class MainActivity extends Activity {
	
	 private Camera mCam = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_main);
	}
	
	@Override
	protected void onStart(){
	  super.onStart();	
	}
	
    public void startBtn(View v) {
      Intent measure = new Intent(MainActivity.this,MeasureActivity.class);
      startActivity(measure);
     }
}
