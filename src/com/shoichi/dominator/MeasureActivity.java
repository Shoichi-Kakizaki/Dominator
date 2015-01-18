package com.shoichi.dominator;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MeasureActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  //フルスクリーン用の設定
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
      requestWindowFeature(Window.FEATURE_NO_TITLE);
      
      //CameraPreviewで作成したSurfaceViewを追加
      LinearLayout CameraSurfaceView = new LinearLayout(this);
      CameraSurfaceView.addView(new CameraPreview(this));
      setContentView(CameraSurfaceView);
	}
	
	@Override
	protected void onStart(){
	  super.onStart();	
	}
	
} 
