package com.shoichi.dominator;

import java.io.IOException;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.Face;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.ViewGroup.LayoutParams;
import android.view.SurfaceView;


@SuppressWarnings("deprecation")
public class CameraPreview extends SurfaceView implements Callback,Camera.FaceDetectionListener  {
	private Camera camera;
	private Activity parent;
	
	public CameraPreview(Context context) {
		super(context);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		parent = (Activity)context;
		//holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	 camera = Camera.open();
	 // リスナーの登録
	 camera.setFaceDetectionListener(this);
	  try {
		camera.setPreviewDisplay(holder);
	  }catch(IOException e) {
	  }
	  
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int f, int w, int h) {
		camera.stopFaceDetection();
		Camera.Parameters param = camera.getParameters();
		param.setPreviewSize(w,h);
		camera.setParameters(param);
		camera.startPreview();
		// 顔検出対応か?
		if (param.getMaxNumDetectedFaces() > 0) {
			// 顔検出開始
			camera.startFaceDetection();
			Log.w("camera","顔検出開始");
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopFaceDetection();
		camera.stopPreview();
		camera.release();
	}
	
	@Override
	public void onFaceDetection(Face[] faces, Camera camera) {
		Log.d("camera", "顔検出メソッド");
		if (faces.length > 0) {
			Log.w("FaceDetection", faces.length + " faces");
			 // MeasureActivityにてaddPermeabilityViewを呼んでいるので、インスタンス変数を参照出来る
			((MeasureActivity)parent).addPermeabilityView(faces);
		}
	}

}