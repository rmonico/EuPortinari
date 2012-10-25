package br.zero.droidcam;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

class MaskSurfaceView extends SurfaceView implements Runnable {

	Thread thread = null;
	SurfaceHolder surfaceHolder;
	volatile boolean running = false;
	private Bitmap bitmap;

	public MaskSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		surfaceHolder = getHolder();
	}

	public void onResumeMySurfaceView() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	public void onPauseMySurfaceView() {
		boolean retry = true;
		running = false;
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				// ... actual drawing on canvas

				canvas.drawColor(Color.WHITE);

				bitmap = BitmapFactory.decodeResource(getResources(),
						R.drawable.mask);

				canvas.drawBitmap(bitmap, 100, 100, null);

				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}

}
