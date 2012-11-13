package br.zero.euportinari;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

public class CameraActivity extends Activity implements PictureCallback {

	private static final String TAG = "MainActivity";
	private Camera mCamera;
	private CameraSurface mCameraSurfaceView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera);

		int frontCameraId = findFrontFacingCamera();

		if (frontCameraId == -1) {
			Toast.makeText(this,
					"Câmera frontal não encontrada, usando camera padrão.",
					Toast.LENGTH_LONG);
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
				Log.d(TAG, "Camera found");
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
			Toast.makeText(getApplicationContext(), e.getMessage(),
					Toast.LENGTH_LONG).show();
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

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {

		File pictureFile = getOutputMediaFile();
		if (pictureFile == null) {
			Log.d(TAG, "Error creating media file, check storage permissions.");
			return;
		}

		try {
			Toast.makeText(this, "onTakePicture", Toast.LENGTH_LONG).show();
			FileOutputStream fos = new FileOutputStream(pictureFile);
			fos.write(data);
			fos.close();

			Toast.makeText(CameraActivity.this,
					"Imagem salva em: " + pictureFile.getName(),
					Toast.LENGTH_LONG).show();

		} catch (FileNotFoundException e) {
			Log.d(TAG, "File not found: " + e.getMessage());
		} catch (IOException e) {
			Log.d(TAG, "Error accessing file: " + e.getMessage());
		}
	}

	private static File getOutputMediaFile() {

		// File mediaStorageDir = new File(
		// Environment.getExternalStoragePublicDirectory(
		//
		// Environment.DIRECTORY_PICTURES), TAG);
		//
		// if (!mediaStorageDir.exists()) {
		//
		// if (!mediaStorageDir.mkdirs()) {
		//
		// Log.d(TAG, "failed to create directory");
		//
		// return null;
		//
		// }
		//
		// }
		//
		// // Create a media file name
		//
		// String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
		// .format(new Date());
		//
		// File mediaFile;
		//
		// mediaFile = new File(mediaStorageDir.getPath() + File.separator +
		//
		// "IMG_" + timeStamp + ".jpg");
		//
		// return mediaFile;
		//
		return new File("/sdcard/external_sd/files/image.jpg");
	}

	public void onTakePicture(View view) {
		mCamera.takePicture(null, null, this);
	}
}
