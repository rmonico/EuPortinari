package br.zero.droidcam;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Camera mCamera;
	private MaskedCameraPreview mPreview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create an instance of Camera
		mCamera = getCameraInstance();

		if (mCamera == null) {
			Toast.makeText(null, "Camera not found", Toast.LENGTH_SHORT).show();
			return;
		}

		// Create our Preview view and set it as the content of our activity.
//		mPreview = new CameraPreview(this, mCamera);
//		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
//		preview.addView(mPreview);
		mPreview = new MaskedCameraPreview(this, mCamera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.addView(mPreview);
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mPreview.onResumeMySurfaceView();
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
		
		mPreview.onPauseMySurfaceView();
	}
}
