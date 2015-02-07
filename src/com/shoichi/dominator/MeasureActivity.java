package com.shoichi.dominator;

import android.app.Activity;
import android.hardware.Camera.Face;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class MeasureActivity extends Activity {
	private CameraPermeabilityView permeabilityView;
	private CameraCoefficientView coefficientView;

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

      //オーバーレイ用のViewを追加
      CameraPermeabilityView  CameraPermeabilityView = new CameraPermeabilityView(this);
      CameraCoefficientView CameraCoefficientView = new CameraCoefficientView(this);
      //自分自身を追加
      this.permeabilityView = CameraPermeabilityView;
      this.coefficientView = CameraCoefficientView;
      addContentView(CameraPermeabilityView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
      addContentView(CameraCoefficientView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	}

	@Override
	protected void onStart(){
	  super.onStart();	
	}

	//addPermeabilityViewをCameraPreviewから呼べるように
	public void addPermeabilityView(Face[] faces) {
		this.permeabilityView.setFaces(faces);
		this.coefficientView.setFaces(faces);
	}
} 
