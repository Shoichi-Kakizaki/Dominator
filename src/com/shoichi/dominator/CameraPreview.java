package com.shoichi.dominator;

import java.io.IOException;
import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

@SuppressWarnings("deprecation")
public class CameraPreview extends SurfaceView implements Callback {
	private Camera camera;
	
	public CameraPreview(Context context) {
		super(context);
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		//holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	  try {
		camera = Camera.open();
		camera.setPreviewDisplay(holder);
	  }catch(IOException e) {
	  }
	  
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int f, int w, int h) {
		Camera.Parameters param = camera.getParameters();
		param.setPreviewSize(w,h);
		camera.setParameters(param);
		camera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		camera.stopPreview();
		camera.release();
	}
	
}