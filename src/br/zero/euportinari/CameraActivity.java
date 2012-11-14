package br.zero.euportinari;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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

public class CameraActivity extends Activity {

	private static final String TAG = "EuPortinari";
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

	public void onTakePicture(View view) {

		PictureCallback jpegCallback = new PictureCallback() {

			@Override
			public void onPictureTaken(byte[] data, Camera camera) {
				Bitmap cameraImage = BitmapFactory.decodeByteArray(data, 0,
						data.length);

				Bitmap mask = BitmapFactory.decodeResource(getResources(),
						R.drawable.mask);

				Bitmap combined = Bitmap.createBitmap(cameraImage.getHeight(),
						cameraImage.getWidth(), Bitmap.Config.ARGB_8888);

				Canvas canvas = new Canvas(combined);

				Matrix matrix = new Matrix();
				matrix.postRotate(270);
				Bitmap rotated = Bitmap.createBitmap(cameraImage, 0, 0,
						cameraImage.getWidth(), cameraImage.getHeight(),
						matrix, true);

				canvas.drawBitmap(rotated, new Matrix(), new Paint());

				Rect src = new Rect(0, 0, mask.getWidth(), mask.getHeight());
				RectF dest = new RectF(0, 0, rotated.getWidth(),
						rotated.getHeight());
				canvas.drawBitmap(mask, src, dest, null);

				canvas.save();

				File pictureFile = getOutputMediaFile();
				if (pictureFile == null) {
					Log.d(TAG,
							"Error creating media file, check storage permissions.");
					return;
				}

				try {
					pictureFile.createNewFile();
				} catch (IOException e) {
					String text = "IOException: " + e.getMessage();
					Toast.makeText(CameraActivity.this, text, Toast.LENGTH_LONG)
							.show();
				}

				try {
					FileOutputStream fos = new FileOutputStream(pictureFile);
					combined.compress(CompressFormat.JPEG, 100, fos);
					fos.close();
					Toast.makeText(
							CameraActivity.this,
							"Imagem salva em: " + pictureFile.getAbsolutePath(),
							Toast.LENGTH_LONG).show();

				} catch (FileNotFoundException e) {
					String text = "File not found: " + e.getMessage();
					Log.d(TAG, text);
					Toast.makeText(CameraActivity.this, text, Toast.LENGTH_LONG)
							.show();
				} catch (IOException e) {
					String text = "Error accessing file: " + e.getMessage();
					Log.d(TAG, text);
					Toast.makeText(CameraActivity.this, text, Toast.LENGTH_LONG)
							.show();
				}
			}

			// private void showText(String text) {
			// Toast.makeText(CameraActivity.this, text, Toast.LENGTH_SHORT)
			// .show();
			// }

			private File getOutputMediaFile() {

				File mediaStorageDir = new File(
						Environment.getExternalStoragePublicDirectory(
						Environment.DIRECTORY_PICTURES), TAG);

				if (!mediaStorageDir.exists()) {

					if (!mediaStorageDir.mkdirs()) {

						Log.d(TAG, "failed to create directory");

						return null;

					}

				}

				// Create a media file name

				String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
						.format(new Date());

				File mediaFile;

				mediaFile = new File(Environment.getExternalStorageDirectory()
						.getPath()
						+ "/external_sd/files/IMG_"
						+ timeStamp
						+ ".jpg");

				return mediaFile;
			}
		};

		mCamera.takePicture(null, null, jpegCallback);
	}
	
}
