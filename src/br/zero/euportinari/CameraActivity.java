package br.zero.euportinari;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CameraActivity extends Activity {

	private static final String DEBUG_TAG = "MainActivity";
	private Camera mCamera;
	private CameraSurface mCameraSurfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		int frontCameraId = findFrontFacingCamera();
		
		if (frontCameraId == -1) {
			Toast.makeText(this, "Câmera frontal não encontrada, usando camera padrão.", Toast.LENGTH_LONG);
			frontCameraId = 0;
		}
		
		// Create an instance of Camera
		mCamera = getCameraInstance(frontCameraId);
		
		if (mCamera == null) {
			Toast.makeText(this, "Camera not found", Toast.LENGTH_SHORT).show();
			return;
		}

		mCamera.setDisplayOrientation(90);

		// Create our Preview view and set it as the content of our activity.
		mCameraSurfaceView = new CameraSurface(this, mCamera);

		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mCameraSurfaceView);
	}

	private int findFrontFacingCamera() {
		int cameraId = -1;
		// Search for the front facing camera
		int numberOfCameras = Camera.getNumberOfCameras();
		for (int i = 0; i < numberOfCameras; i++) {
			CameraInfo info = new CameraInfo();
			Camera.getCameraInfo(i, info);
			if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
				Log.d(DEBUG_TAG, "Camera found");
				cameraId = i;
				break;
			}
		}
		return cameraId;
	}

	/** A safe way to get an instance of the Camera object. */
	public Camera getCameraInstance(int cameraId) {
		Camera c = null;
		try {
			c = Camera.open(cameraId); // attempt to get a Camera instance
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
		return c; // returns null if camera is unavailable
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		if (mCamera != null) {
			mCamera.release();
			mCamera = null;
		}
		super.onPause();
	}
}
